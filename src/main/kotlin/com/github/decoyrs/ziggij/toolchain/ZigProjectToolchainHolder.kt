package com.github.decoyrs.ziggij.toolchain

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.ServiceManager
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(): String {
    return try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(60, TimeUnit.MINUTES)
        proc.inputStream.bufferedReader().readText()
    } catch(e: IOException) {
        e.printStackTrace()
        ""
    }
}

object PathAsStringSerializer : KSerializer<File> {
    override val descriptor = PrimitiveSerialDescriptor("File", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: File) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder) = File(decoder.decodeString())
}

@Serializable
data class ZigToolchainInfo(
    @Serializable(with = PathAsStringSerializer::class)
    val zig_exe:File,
    @Serializable(with = PathAsStringSerializer::class)
    val lib_dir:File,
    @Serializable(with = PathAsStringSerializer::class)
    val std_dir:File,
    @Serializable(with = PathAsStringSerializer::class)
    val global_cache_dir:File,
    val version:String
)

@Service(Service.Level.APP)
class ZigProjectToolchainHolder {
    companion object{
        val Instance: ZigProjectToolchainHolder = ServiceManager.getService(ZigProjectToolchainHolder::class.java)
    }
    fun getToolchainInfo():ZigToolchainInfo {
        val pathToZigBin = "D:/PROGRAMS/zig/zig-windows-x86_64-0.8.0-dev.2275+8467373bb/zig.exe"
        val output = "$pathToZigBin env".runCommand()
        return Json.decodeFromString(ZigToolchainInfo.serializer(), output)
    }
}