package net.logiico.formnativeandroidjava.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Template")
public class RoomTemplate {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "template_content_model")
    public String templateContent;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "version")
    public int version;


    public RoomTemplate() {
    }

}
