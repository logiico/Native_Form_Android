package com.example.nativeformmakerandroid.model.NativeForm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohammad MBR on 11/12/2017.
 */

public class FileModelJavaScript implements Parcelable {
    public static final Creator<FileModelJavaScript> CREATOR = new Creator<FileModelJavaScript>() {
        @Override
        public FileModelJavaScript createFromParcel(Parcel in) {
            return new FileModelJavaScript(in);
        }

        @Override
        public FileModelJavaScript[] newArray(int size) {
            return new FileModelJavaScript[size];
        }
    };
    public String Uid;
    public String InputName;
    public String Title;
    public boolean Multiple;
    public List<String> filePaths;
    public int status;
    public int upload_percent = 0;

    public FileModelJavaScript() {
    }

    protected FileModelJavaScript(Parcel in) {
        Uid = in.readString();
        InputName = in.readString();
        Title = in.readString();
        Multiple = in.readByte() != 0;
        filePaths = in.createStringArrayList();
        status = in.readInt();
        upload_percent = in.readInt();
    }

//    public static void insertOrUpdate(FileModelJavaScript fileModelJavaScript) {
//        // SqliteHelper sqliteHelper = new SqliteHelper(MyApplication.getInstance(), ConstHelper.DATABASE_PATH, ConstHelper.DATABASE_NAME);
//        try {
//            for (int i = 0; i < fileModelJavaScript.filePaths.size(); i++) {
//                String filePath = fileModelJavaScript.filePaths.get(i);
//                FileModel fileModel = new FileModel();
//                fileModel.Uid = fileModelJavaScript.Uid;
//                fileModel.InputName = fileModelJavaScript.InputName;
//                fileModel.filePath = filePath;
//                fileModel.upload_percent = fileModelJavaScript.upload_percent;
//                String[] splited = fileModelJavaScript.filePaths.get(i).split("\\.");
//                fileModel.Title = fileModelJavaScript.Title;//fileModelJavaScript.Uid + fileModelJavaScript.InputName + i + "." + splited[splited.length - 1];
//                fileModel.Multiple = fileModelJavaScript.Multiple;
//                fileModel.status = fileModelJavaScript.status;
//                FileModel.insertOrUpdateWithStatus(fileModel);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ArrayList<FileModelJavaScript> getFileModels(String uid) {
//
//        try {
//            List<FileModel> fileModels = FileModel.getFileModels(uid);
//            ArrayList<FileModelJavaScript> fileModelJavaScripts = new ArrayList<>();
//            if (fileModels != null) {
//                for (FileModel fileModel : fileModels) {
//                    boolean exist = false;
//                    for (FileModelJavaScript fileModelJavaScript : fileModelJavaScripts) {
//                        if (fileModel.InputName.equals(fileModelJavaScript.InputName)) {
//                            exist = true;
//                            if (fileModelJavaScript.filePaths == null) {
//                                fileModelJavaScript.filePaths = new ArrayList<>();
//                            }
//                            fileModelJavaScript.filePaths.add(fileModel.filePath);
//                            break;
//                        }
//                    }
//                    if (!exist) {
//                        FileModelJavaScript fileModelJavaScript = new FileModelJavaScript();
//                        fileModelJavaScript.Uid = fileModel.Uid;
//                        fileModelJavaScript.InputName = fileModel.InputName;
//                        fileModelJavaScript.filePaths = new ArrayList<>();
//                        fileModelJavaScript.filePaths.add(fileModel.filePath);
//                        fileModelJavaScript.status = fileModel.status;
//                        fileModelJavaScript.upload_percent = fileModel.upload_percent;
//                        fileModelJavaScript.Title = fileModel.Title;
//                        fileModelJavaScript.Multiple = fileModel.Multiple;
//                        fileModelJavaScripts.add(fileModelJavaScript);
//                    }
//                }
//            }
//            return fileModelJavaScripts;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Uid);
        parcel.writeString(InputName);
        parcel.writeString(Title);
        parcel.writeByte((byte) (Multiple ? 1 : 0));
        parcel.writeStringList(filePaths);
        parcel.writeInt(status);
        parcel.writeInt(upload_percent);
    }



}
