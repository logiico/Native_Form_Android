package net.logiico.formnativeandroidjava.model;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FileModelDao extends BaseDao<RoomFileModel> {

    @Query("SELECT * FROM FileModel WHERE u_id LIKE :uId")
    List<RoomFileModel> getListByUid(String uId);

    @Query("SELECT * FROM FileModel WHERE u_id LIKE :uId AND title LIKE :title")
    RoomFileModel getByUid_Title(String uId, String title);

    @Query("SELECT * FROM FileModel WHERE status IN (2, 4) AND number_of_failed_sends < 6")
        // status SAVED = 2, Failed = 4
    List<RoomFileModel> getNotSentList();

    @Query("UPDATE FileModel SET status = 2 WHERE u_id LIKE :uID")
        // status SAVED = 2
    int setSaved(String uID);

    @Query("UPDATE FileModel SET number_of_failed_sends = :errorCount WHERE u_id LIKE :uID AND title LIKE :title")
        // status SAVED = 2
    int submitErrorSending(String uID, String title, int errorCount);
}
