


package com.example.nativeformmakerandroid.model.NativeForm;



/**
 * Created by Mohammad Taha on 17/3/2019.
 * FileModelMission is somehow similar to FileModel but FileModel is very much dependent on FORM to save and send data
 * so we decided to seperate database and uploader of mission files from mission/form files
 * <p>
 * <p>
 * there would be new FileModelMission - UploaderMissionFile - UploadFileMissionFinishListener - UploadFileModelMissionObservable
 * also new Table seperated from Filemodel table
 */

public class FileModelMission {
    public static final String sTable = "FileModelMissionTable";
    public static final String TAG = "FileModelMissionTAG";
    public static final String TAG_valid = "dataSentRes_ٰValidMiss";

    public static final String sUploadPercentage = "upload_percent";
    //  public static final String sMultiple = "multiple";
    public static final String sTitle = "title";
    public static final String sUid = "uid";
    public static final String sInputName = "input_name"; // Description/note of filemodelmission
    public static final String sFilePath = "file_path";
    public static final String sStatus = "status";
    public static final String sMissionId = "mission_id"; // which mission
    public static final String sFileType = "file_type"; // signature or picture

    public String Uid;
    public String InputName; // Description/note of filemodelmission
    public String filePath;
    public int status;
    public int upload_percent;
    public String Title;
    // public boolean Multiple;
    public String mission_id;
    public String file_type;


//    public static void insertOrUpdate(FileModelMission fileModel) {
//        // SqliteHelper sqliteHelper = new SqliteHelper(MyApplication.getInstance(), ConstHelper.DATABASE_PATH, ConstHelper.DATABASE_NAME);
//        try {
//            LogHelper.e(TAG, new Gson().toJson(fileModel));
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(sUid, fileModel.Uid);
//            contentValues.put(sInputName, fileModel.InputName);
//            contentValues.put(sFilePath, fileModel.filePath);
//            contentValues.put(sStatus, FileMissionStatus.SAVED.getIntValue());
//            contentValues.put(sUploadPercentage, fileModel.upload_percent);
//            contentValues.put(sTitle, fileModel.Title);
//            //  contentValues.put(sMultiple, fileModel.Multiple);
//            contentValues.put(sMissionId, fileModel.mission_id);
//            contentValues.put(sFileType, fileModel.file_type);
//
//            try {
//                long ali = MyApplication.dbApp.dataBase.insert(sTable, null, contentValues);
//            } catch (Exception e) {
//                MyApplication.dbApp.dataBase.update(sTable, contentValues, sUid + " = \"" + fileModel.Uid + "\" AND " + sInputName + " = \"" + fileModel.InputName + "\" AND " + sUploadPercentage + " = \"" + fileModel.upload_percent + "\"", null);
//            }
//        } catch (Exception e) {
//
//
//            e.printStackTrace();
//        }
//    }

//    public static void insertOrUpdateWithStatus(FileModelMission fileModel) {
//        // SqliteHelper sqliteHelper = new SqliteHelper(MyApplication.getInstance(), ConstHelper.DATABASE_PATH, ConstHelper.DATABASE_NAME);
//        try {
//            LogHelper.e(TAG, new Gson().toJson(fileModel));
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(sUid, fileModel.Uid);
//            contentValues.put(sInputName, fileModel.InputName);
//            contentValues.put(sFilePath, fileModel.filePath);
//            contentValues.put(sStatus, fileModel.status);
//            contentValues.put(sUploadPercentage, fileModel.upload_percent);
//            contentValues.put(sTitle, fileModel.Title);
//            //    contentValues.put(sMultiple, fileModel.Multiple);
//            contentValues.put(sMissionId, fileModel.mission_id);
//            contentValues.put(sFileType, fileModel.file_type);
//
//            try {
//                long ali = MyApplication.dbApp.dataBase.insertOrThrow(sTable, null, contentValues);
//            } catch (Exception e) {
//                MyApplication.dbApp.dataBase.update(sTable, contentValues, sUid + " = \"" + fileModel.Uid + "\" AND " + sInputName + " = \"" + fileModel.InputName + "\" AND " + sFilePath + " = \"" + fileModel.filePath + "\"", null);
//            }
//        } catch (Exception e) {
//
//
//            e.printStackTrace();
//        }
//    }
//    public static void update(FileModelMission fmm) {
//
//        FileModelMission fileModel = new FileModelMission();
//        fileModel.Uid = fmm.Uid; // ok
//        fileModel.InputName = fmm.InputName;
//        fileModel.mission_id = fmm.mission_id;
//        fileModel.filePath = fmm.filePath;
//        fileModel.upload_percent = fmm.upload_percent;
//        String[] splited = fmm.filePath.split("\\.");
//        fileModel.Title = fileModel.Uid /*+ fileModel.InputName*/ + "." + splited[splited.length - 1];
//        fileModel.file_type = fmm.file_type;
//        // TODO I CHANGED IT TO TEMP IT WAS SAVED
//        fileModel.status = fmm.status;
//        FileModelMission.insertOrUpdateWithStatus(fileModel);
//    }
//    //todo : need to be tested
//    public static boolean DeleteFileModelMissionByUid(FileModelMission fmm) {
//        LogHelper.d(TAG, " Deleting Uid: " + fmm.Uid + " + filePath: " + fmm.filePath + " + mission_id: " + fmm.mission_id + " + file_type: " + fmm.file_type);
//        try {
//
//            try {
//                MyApplication.dbApp.dataBase.delete(sTable,
//                        fmm.sUid + " = ?",
//                        new String[]{fmm.Uid});
//                LogHelper.d(TAG, " Delete Done " + fmm.Uid + " + filePath: " + fmm.filePath + " + mission_id: " + fmm.mission_id + " + file_type: " + fmm.file_type);
//                return true;
//            } catch (Exception e) {
//                LogHelper.d(TAG, " Deleting not Done try/catch" + fmm.Uid + " + filePath: " + fmm.filePath + " + mission_id: " + fmm.mission_id + " + file_type: " + fmm.file_type);
//                return false;
//            }
//        } catch (SQLException e) {
//            return false;
//        }
//
//    }
//
//    /**
//     * checks if mission already has signature saved or not
//     */
//    public static List<FileModelMission> getSignatureOfThisMission(String Mission_uid) {
//
//      //  try {
//            List<FileModelMission> fileModels = new ArrayList<>();
//            Cursor cursor = MyApplication.dbApp.dataBase.query(sTable, null,
//                    sMissionId + " = '" + Mission_uid + "'  " + " AND " + sFileType + " = '" + ActivityComplete.fileType_signature + "'", null, null, null, null);
//            cursor.moveToFirst();
//            if (cursor.getCount() > 0) {
//                do {
//                    fileModels.add(readFromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//            LogHelper.e(TAG, new Gson().toJson(fileModels) + cursor.getCount());
//            return fileModels;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
//    }
//
//    public static List<FileModelMission> getFileModelMissionByUid(String uid) {
//
//        try {
//            List<FileModelMission> fileModels = new ArrayList<>();
//            Cursor cursor = MyApplication.dbApp.dataBase.query(sTable, null,
//                    sUid + " = '" + uid + "'  " /*"' AND " + sStatus + " = " + String.valueOf(FileStatus.SAVED.getIntValue())*/, null, null, null, null);
//            cursor.moveToFirst();
//            if (cursor.getCount() > 0) {
//                do {
//                    fileModels.add(readFromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//            //  LogHelper.e(TAG, uid + new Gson().toJson(fileModels) + cursor.getCount());
//            return fileModels;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * get files of missionId
//     */
//    public static List<FileModelMission> getFileModelsByMissionId(String mission_id) {
//
//      //  try {
//            List<FileModelMission> fileModels = new ArrayList<>();
//            Cursor cursor = MyApplication.dbApp.dataBase.query(sTable, null,
//                    sMissionId + " = '" + mission_id + "'  " /*"' AND " + sStatus + " = " + String.valueOf(FileStatus.SAVED.getIntValue())*/, null, null, null, null);
//            cursor.moveToFirst();
//            if (cursor.getCount() > 0) {
//                do {
//                    fileModels.add(readFromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//            LogHelper.e(TAG, mission_id + " : " + new Gson().toJson(fileModels) + cursor.getCount());
//            return fileModels;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
//    }
//
//    public static List<FileMissionAttachmentModel> getMissionFilesAttachmentList(String mission_id) {
//
//        List<FileMissionAttachmentModel> f_atach = new ArrayList<>();
//        List<FileModelMission> fmm_list = FileModelMission.getFileModelsByMissionId(mission_id);
//        LogHelper.d("SetStatusList", fmm_list == null ? "null" : String.valueOf(fmm_list.size()) + "mission_id: " + mission_id);
//        for (int i = 0; i < fmm_list.size(); i++) {
//            FileMissionAttachmentModel fm = new FileMissionAttachmentModel();
//            fm.IsSignature = fmm_list.get(i).file_type.contentEquals(ActivityComplete.fileType_signature) ? true : false;
//            // LogHelper.d("SetStatusList","file_type: "+ fmm_list.get(i).file_type +" file_type_String: "+  String.valueOf(Boolean.valueOf(fmm_list.get(i).file_type)));
//
//            fm.Uid = fmm_list.get(i).Uid;
//            fm.Description = fmm_list.get(i).InputName == null ? "" : fmm_list.get(i).InputName;
//            f_atach.add(fm);
//        }
//        return f_atach;
//    }
//
//    public static List<FileModelMission> getSavedFileModelsOfSentSuccessfullMission() {
//        Cursor cursor = null;
//        try {
//            List<FileModelMission> fileModels = new ArrayList<>();
//            cursor = MyApplication.dbApp.dataBase.query(sTable, null,
//                    /*"' AND " + */sStatus + " = " + String.valueOf(FileMissionStatus.SAVED.getIntValue()), null, null, null, sUid + " DESC");
//            LogHelper.d("missionStatuses.isEmpty 9", "before cursor FileModelMission");
//
//            if (cursor.getCount() > 0) {
//                LogHelper.d("missionStatuses.isEmpty 9", "cursor.getCount()>0");
//                cursor.moveToFirst();
//
//                do {
//                    final FileModelMission fm = readFromCursor(cursor);
////                    fm = readFromCursor(cursor);
//                    LogHelper.d("missionStatuses.isEmpty 9", "cursor.getCount()>0" + "file model mission_id: " + fm.mission_id + " status: " + fm.status);
//
//                    /**
//                     dows the owner mission is sent already
//                     */
//                    MissionStatus ms = MissionStatus.GetMissionBy_MissionId_And_mission_send_status(Integer.parseInt(fm.mission_id), EnumSyncSendingStatus.Successfull.getIntValue());
////                    Mission ms = Mission.GetMissionWithId(fm.mission_id);
////                    Mission.GetMissionWithId(Integer.parseInt(fm.mission_id), new GetMissionsCallback() {
////                        @Override
////                        public void missionsReady(List<Mission> missions) {
////                            LogHelper.d("missionStatuses.isEmpty 20", "cursor.getCount()>0" + "file model mission_id: " + fm.mission_id+ "\n" + new Gson().toJson(missions));
////
////                        }
////                    });
//                    if (ms != null)
//                        fileModels.add(fm);
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//            LogHelper.e(TAG, new Gson().toJson(fileModels));
//            return fileModels;
//        } catch (Exception e) {
//            if (cursor != null)
//                cursor.close();
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static FileModelMission readFromCursor(Cursor cursor) {
//        try {
//            FileModelMission fileModel = new FileModelMission();
//            fileModel.Uid = cursor.getString(cursor.getColumnIndex(sUid));
//            fileModel.InputName = cursor.getString(cursor.getColumnIndex(sInputName));
//            fileModel.filePath = cursor.getString(cursor.getColumnIndex(sFilePath));
//            fileModel.status = cursor.getInt(cursor.getColumnIndex(sStatus));
//            fileModel.upload_percent = cursor.getInt(cursor.getColumnIndex(sUploadPercentage));
//            fileModel.Title = cursor.getString(cursor.getColumnIndex(sTitle));
//            //     fileModel.Multiple = cursor.getInt(cursor.getColumnIndex(sMultiple)) == 1;
//            fileModel.mission_id = cursor.getString(cursor.getColumnIndex(sMissionId));
//            fileModel.file_type = cursor.getString(cursor.getColumnIndex(sFileType));
//
//            return fileModel;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static void setFileSent(FileModelMission fileModel) {
//
//        try {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(sUid, fileModel.Uid);
//            contentValues.put(sInputName, fileModel.InputName);
//            contentValues.put(sFilePath, fileModel.filePath);
//            contentValues.put(sStatus, FileMissionStatus.SENT.intValue);
//            LogHelper.d("setsetset", fileModel.Uid + " > fileindex = " + fileModel.status + " , path>>" + fileModel.filePath);
//
///*
//            SqliteHelper sqliteHelper = new SqliteHelper(MyApplication.getInstance(), ConstHelper.DATABASE_PATH, ConstHelper.DATABASE_NAME);
//*/
//            MyApplication.dbApp.dataBase.update(sTable, contentValues, sUid + " = \"" + fileModel.Uid + "\" AND " + sInputName + " = \"" + fileModel.InputName + "\" AND " + sFilePath + " = \"" + fileModel.filePath + "\"", null);
//            LogHelper.d("setsetset", "ok");
//
//        } catch (Exception e) {
//            LogHelper.d("setsetset", "error");
//
//            e.printStackTrace();
//        }
//    }
//
//    public static void setFileNotSuccessful(FileModelMission fileModel) {
//
//        try {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(sUid, fileModel.Uid);
//            contentValues.put(sInputName, fileModel.InputName);
//            contentValues.put(sFilePath, fileModel.filePath);
//            contentValues.put(sStatus, FileMissionStatus.NOT_SUCCESSFUL.intValue);
//            LogHelper.d("setsetset", fileModel.Uid + " > fileindex = " + fileModel.status + " , path>>" + fileModel.filePath);
//
///*
//            SqliteHelper sqliteHelper = new SqliteHelper(MyApplication.getInstance(), ConstHelper.DATABASE_PATH, ConstHelper.DATABASE_NAME);
//*/
//            MyApplication.dbApp.dataBase.update(sTable, contentValues, sUid + " = \"" + fileModel.Uid + "\" AND " + sInputName + " = \"" + fileModel.InputName + "\" AND " + sFilePath + " = \"" + fileModel.filePath + "\"", null);
//            LogHelper.d("setsetset", "ok");
//
//        } catch (Exception e) {
//            LogHelper.d("setsetset", "error");
//
//            e.printStackTrace();
//        }
//    }
//
//    public enum FileMissionStatus {
//        SENT(1), SAVED(2), TEMPORARY(3), NOT_SUCCESSFUL(4);
//        private int intValue;
//
//        FileMissionStatus(int intValue) {
//            this.intValue = intValue;
//
//        }
//
//        public static FileMissionStatus fromInt(int i) {
//            for (FileMissionStatus b : FileMissionStatus.values()) {
//                if (b.getIntValue() == (i)) {
//                    return b;
//                }
//            }
//            return null;
//        }
//
//        public int getIntValue() {
//            return intValue;
//        }
//
//    }
}

