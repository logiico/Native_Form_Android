package net.logiico.formnativeandroidjava.model;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TemplateResultDao extends BaseDao<RoomTemplateResult> {

    /* Note: if mission id is null or 0 the template data belongs to Inspection NOT a particular mission */
    @Query("SELECT * FROM TemplateResult WHERE mission_id is null OR mission_id LIKE 0 ORDER BY last_activity_time DESC")
    List<RoomTemplateResult> getAllInspection();

    @Query("SELECT * FROM TemplateResult WHERE data_status_server LIKE :statusInServer AND (mission_id is null OR mission_id LIKE 0) ORDER BY last_activity_time DESC")
    List<RoomTemplateResult> getAllByServerStatus(int statusInServer);

    /* Note: if mission id is not equal to 0 the template data belongs to a Mission NOT Inspection */
    @Query("SELECT * FROM TemplateResult WHERE mission_id NOT LIKE 0 ORDER BY last_activity_time DESC LIMIT :limit")
    List<RoomTemplateResult> getAllMission(String limit);

    @Query("SELECT * FROM TemplateResult WHERE mission_id NOT LIKE 0 AND data_status_app LIKE :statusInApp")
    List<RoomTemplateResult> getAllMissionByAppStatus(int statusInApp);

    @Query("SELECT * FROM TemplateResult WHERE mission_id LIKE :missionId")
    List<RoomTemplateResult> getByMissionId(int missionId);

    @Query("SELECT * FROM TemplateResult WHERE u_id LIKE :uId")
    RoomTemplateResult getByUId(String uId);

    @Query("SELECT * FROM TemplateResult WHERE mission_id LIKE :missionId AND data_status_app LIKE :statusInApp")
    RoomTemplateResult getByMissionId_StatusInApp(int missionId, int statusInApp);

    @Update(onConflict = REPLACE)
    int update(RoomTemplateResult templateResult);

    @Transaction
    default List<RoomTemplateResult> getByMissionIdTransaction(int missionId) {
        FileModelDao fileModelDao = AppDatabase.getAppDatabase().fileModelDao();

        List<RoomTemplateResult> templateResultList = getByMissionId(missionId);

        for (RoomTemplateResult templateResult : templateResultList) {
            templateResult.fileModelList = fileModelDao.getListByUid(templateResult.uId);
        }
        return templateResultList;
    }

    @Transaction
    default void insertTransaction(RoomTemplateResult templateResult) {

        RoomTemplateResult dbTemplateResult = getByUId(templateResult.uId);
        if (dbTemplateResult == null || (dbTemplateResult.statusInApp != 1 && dbTemplateResult.statusInApp != 3)) { // SAVED = 1 , SENT = 3
            insert(templateResult);
            if (templateResult.fileModelList != null && templateResult.fileModelList.size() > 0)
                AppDatabase.getAppDatabase().fileModelDao().insertAll(templateResult.fileModelList);
        }


    }
}
