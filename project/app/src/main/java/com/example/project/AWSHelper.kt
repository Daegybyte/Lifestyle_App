package com.example.project

import android.app.Application
import android.util.Log
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.StorageException
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.result.StorageDownloadFileResult
import com.amplifyframework.storage.result.StorageTransferProgress
import com.amplifyframework.storage.result.StorageUploadFileResult
import java.io.File

object AWSHelper {
    fun backupRoom(application: Application) {
        val dbFile = File(application.getDatabasePath("room_database").absolutePath)
        Amplify.Storage.uploadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackup",
            dbFile,
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
            dbWalFile,
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
            dbShmFile,
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
        Amplify.Storage.downloadFile(
            Amplify.Auth.currentUser.userId + "/RoomBackup",
            dbFile,
            StorageDownloadFileOptions.defaultInstance(),
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
            dbWalFile,
            StorageDownloadFileOptions.defaultInstance(),
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
            dbShmFile,
            StorageDownloadFileOptions.defaultInstance(),
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