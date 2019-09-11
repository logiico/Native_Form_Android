package net.logiico.formnativeandroidjava.model;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TemplateDao extends BaseDao<RoomTemplate> {


    @Query("SELECT * FROM Template WHERE id LIKE :templateId")
    RoomTemplate getTemplateById(int templateId);

    @Query("SELECT * FROM Template")
    List<RoomTemplate> getAllTemplates();

    @Query("SELECT template_content_model FROM Template WHERE id = :templateId")
    String getTemplateContentModelById(int templateId);

    @Query("UPDATE Template SET template_content_model = :templateContent WHERE id = :templateId")
    void updateTemplateContentModel(int templateId, String templateContent);

    @Update(onConflict = REPLACE)
    void update(RoomTemplate template);

    @Query("DELETE FROM Template")
    void truncate();
}
