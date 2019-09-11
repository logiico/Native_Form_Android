package net.logiico.formnativeandroidjava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.io.Serializable;

@Entity(tableName = "FileModel", primaryKeys = {"u_id", "input_name", "file_path"},
        indices = {@Index(value = {"u_id"})},
        foreignKeys = @ForeignKey(entity = RoomTemplateResult.class, parentColumns = "u_id", childColumns = "u_id", onDelete = ForeignKey.CASCADE))
public class RoomFileModel implements Serializable {

    @NonNull
    @ColumnInfo(name = "u_id")
    public String uId;

    @NonNull
    @ColumnInfo(name = "input_name")
    public String inputName;

    @NonNull
    @ColumnInfo(name = "file_path")
    public String filePath;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "upload_percent")
    public int uploadPercent;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "multiple")
    public boolean multiple;

    @ColumnInfo(name = "number_of_failed_sends")
    public int numberOfFailedSends;

    public RoomFileModel() {
    }

    public enum EnumFileStatus {
        SENT(1), SAVED(2), TEMPORARY(3), FAILED(4);
        private int intValue;

        EnumFileStatus(int intValue) {
            this.intValue = intValue;

        }

        public static EnumFileStatus fromInt(int i) {
            for (EnumFileStatus b : EnumFileStatus.values()) {
                if (b.getIntValue() == (i)) {
                    return b;
                }
            }
            return null;
        }

        public int getIntValue() {
            return intValue;
        }

    }

}
