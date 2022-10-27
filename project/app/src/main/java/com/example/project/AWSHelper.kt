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
            "RoomBackup",
            dbFile,
            { result: StorageUploadFileResult ->
                Log.i("MyAmplifyApp", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            }
        )
        val dbWalFile = File(dbFile.path + "-wal")
        Amplify.Storage.uploadFile(
            "RoomBackupWAL",
            dbWalFile,
            { result: StorageUploadFileResult ->
                Log.i("MyAmplifyApp", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            }
        )
        val dbShmFile = File(dbFile.path + "-shm")
        Amplify.Storage.uploadFile(
            "RoomBackupSHM",
            dbShmFile,
            { result: StorageUploadFileResult ->
                Log.i("MyAmplifyApp", "Successfully uploaded: " + result.key)
            },
            { storageFailure: StorageException? ->
                Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            }
        )
    }

    fun loadFromBackup(path: String) {
        Amplify.Storage.downloadFile(
            "ExampleKey",
            File(path),
            StorageDownloadFileOptions.defaultInstance(),
            { progress: StorageTransferProgress ->
                Log.i("MyAmplifyApp", "Fraction completed: " + progress.fractionCompleted)
            },
            { result: StorageDownloadFileResult ->
                Log.i("MyAmplifyApp", "Successfully downloaded: " + result.file.name)
            },
            { error: StorageException? -> Log.e("MyAmplifyApp", "Download Failure", error) }
        )
    }
}