@file:Suppress("HardCodedStringLiteral")

package org.zig.yazb.toolchain

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.Logger
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

@Suppress("SpreadOperator")
fun String.runCommand(logger: Logger): String {
    return try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        val hour: Long = 60
        proc.waitFor(hour, TimeUnit.MINUTES)
        proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        logger.error("Failed to run command $this", e)
        ""
    }
}

object PathAsStringSerializer : KSerializer<File> {
    override val descriptor = PrimitiveSerialDescriptor("File", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: File) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder) = File(decoder.decodeString())
}

@Serializable
@Suppress("ConstructorParameterNaming")
data class ZigToolchainInfo(
    @Serializable(with = PathAsStringSerializer::class)
    val zig_exe: File,
    @Serializable(with = PathAsStringSerializer::class)
    val lib_dir: File,
    @Serializable(with = PathAsStringSerializer::class)
    val std_dir: File,
    @Serializable(with = PathAsStringSerializer::class)
    val global_cache_dir: File,
    val version: String
)

@Service(Service.Level.APP)
class ZigProjectToolchainHolder {
    companion object {
        val Instance: ZigProjectToolchainHolder = ServiceManager.getService(ZigProjectToolchainHolder::class.java)
        val logger = Logger.getInstance(ZigProjectToolchainHolder::class.java)
    }
    fun getToolchainInfo(): ZigToolchainInfo {
        val pathToZigBin = "C:/Users/jetbrains/zig-windows-x86_64-0.8.0-dev.2133+ad33e3483/zig.exe"
        val output = "$pathToZigBin env".runCommand(logger)
        return Json.decodeFromString(ZigToolchainInfo.serializer(), output)
    }
}
