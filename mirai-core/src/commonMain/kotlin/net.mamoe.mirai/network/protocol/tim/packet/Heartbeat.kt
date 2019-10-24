@file:Suppress("EXPERIMENTAL_UNSIGNED_LITERALS", "EXPERIMENTAL_API_USAGE")

package net.mamoe.mirai.network.protocol.tim.packet

import kotlinx.io.core.BytePacketBuilder
import kotlinx.io.core.ByteReadPacket
import net.mamoe.mirai.network.protocol.tim.TIMProtocol
import net.mamoe.mirai.utils.io.encryptAndWrite
import net.mamoe.mirai.utils.io.writeHex
import net.mamoe.mirai.utils.io.writeQQ

@PacketId(0x00_58u)
class HeartbeatPacket(
        private val bot: UInt,
        private val sessionKey: ByteArray
) : OutgoingPacket() {
    override fun encode(builder: BytePacketBuilder) = with(builder) {
        writeQQ(bot)
        writeHex(TIMProtocol.fixVer)
        encryptAndWrite(sessionKey) {
            writeHex("00 01 00 01")
        }
    }

    @PacketId(0x00_58u)
    class Response(input: ByteReadPacket) : ResponsePacket(input)
}