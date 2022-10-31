package com.example.project

import android.app.Application
import android.util.Log
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.StorageAccessLevel
import com.amplifyframework.storage.StorageException
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.options.StorageUploadFileOptions
import com.amplifyframework.storage.result.StorageDownloadFileResult
import com.amplifyframework.storage.result.StorageTransferProgress
import com.amplifyframework.storage.result.StorageUploadFileResult
import java.io.File

object AWSHelper {
    fun backupRoom(application: Application) {
        val dbFile = File(application.getDatabasePath("room_database").absolutePath)
//        val options = StorageUploadFileOptions.builder().accessLevel(StorageAccessLevel.PRIVATE).build()
        val options = StorageUploadFileOptions.defaultInstance()
        Amplify.Storage.uploadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackup",
//            "RoomBackup",
            dbFile,
            options,
            { result: StorageUploadFileResult ->
                Log.i("AWSHelperUpload", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("AWSHelperUpload", "Upload failed", storageFailure)
            }
        )
        val dbWalFile = File(dbFile.path + "-wal")
        Amplify.Storage.uploadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackupWAL",
//            "RoomBackupWAL",
            dbWalFile,
            options,
            { result: StorageUploadFileResult ->
                Log.i("AWSHelperUpload", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("AWSHelperUpload", "Upload failed", storageFailure)
            }
        )
        val dbShmFile = File(dbFile.path + "-shm")
        Amplify.Storage.uploadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackupSHM",
//            "RoomBackupSHM",
            dbShmFile,
            options,
            { result: StorageUploadFileResult ->
                Log.i("AWSHelperUpload", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("AWSHelperUpload", "Upload failed", storageFailure)
            }
        )
    }

    fun loadFromBackup(application: Application) {
        val dbFile = File(application.getDatabasePath("room_database").absolutePath)
//        val options = StorageDownloadFileOptions.builder().accessLevel(StorageAccessLevel.PRIVATE).build()
        val options = StorageDownloadFileOptions.defaultInstance()
        Amplify.Storage.downloadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackup",
//            "RoomBackup",
            dbFile,
            options,
            { progress: StorageTransferProgress ->
                Log.i("AWSHelperDownload", "Fraction completed: " + progress.fractionCompleted)
            },
            { result: StorageDownloadFileResult ->
                Log.i("AWSHelperDownload", "Successfully downloaded: " + result.file.name)
            },
            { error: StorageException? ->
                Log.e("AWSHelperDownload", "Download Failure", error)
            }
        )
        val dbWalFile = File(dbFile.path + "-wal")
        Amplify.Storage.downloadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackupWAL",
//            "RoomBackupWAL",
            dbWalFile,
            options,
            { progress: StorageTransferProgress ->
                Log.i("AWSHelperDownload", "Fraction completed: " + progress.fractionCompleted)
            },
            { result: StorageDownloadFileResult ->
                Log.i("AWSHelperDownload", "Successfully downloaded: " + result.file.name)
            },
            { error: StorageException? ->
                Log.e("AWSHelperDownload", "Download Failure", error)
            }
        )
        val dbShmFile = File(dbFile.path + "-shm")
        Amplify.Storage.downloadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackupSHM",
//            "RoomBackupSHM",
            dbShmFile,
            options,
            { progress: StorageTransferProgress ->
                Log.i("AWSHelperDownload", "Fraction completed: " + progress.fractionCompleted)
            },
            { result: StorageDownloadFileResult ->
                Log.i("AWSHelperDownload", "Successfully downloaded: " + result.file.name)
            },
            { error: StorageException? ->
                Log.e("AWSHelperDownload", "Download Failure", error)
            }
        )
    }
}