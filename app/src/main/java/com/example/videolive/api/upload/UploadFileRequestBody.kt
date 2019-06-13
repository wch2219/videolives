package com.hg.kotlin.api.upload

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.*
import java.io.File
import java.io.IOException

class UploadFileRequestBody(file: Any?, fileUploadObserver: FileUploadObserver<ResponseBody>): RequestBody() {

    private var mRequestBody: RequestBody?=null
    private var fileUploadObserver: FileUploadObserver<ResponseBody>?=null
    init {
        if (file is String) {

            this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        }else if (file is File){
            this.mRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file)
//        this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        }

        this.fileUploadObserver = fileUploadObserver
    }


    override fun contentType(): MediaType? {
        return mRequestBody?.contentType()
    }

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return mRequestBody?.contentLength()!!
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val countingSink = CountingSink(sink)
        val bufferedSink = Okio.buffer(countingSink)
        //写入
        mRequestBody?.writeTo(bufferedSink)

        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush()
    }

    protected inner class CountingSink(delegate: Sink) : ForwardingSink(delegate) {

        private var bytesWritten: Long = 0

        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)

            bytesWritten += byteCount
            fileUploadObserver?.onProgressChange(bytesWritten, contentLength())
        }
    }
}