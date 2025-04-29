package com.mio.netmusic.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SongResponse(
    val code: Int? = null,
    val privileges: List<Privilege?>? = null,
    val songs: List<Song?>? = null
) {
    @Serializable
    data class Privilege(
        val chargeInfoList: List<ChargeInfo?>? = null,
        val code: Int? = null,
        val cp: Int? = null,
        val cs: Boolean? = null,
        val dl: Int? = null,
        val dlLevel: String? = null,
        val dlLevels: String? = null,
        val downloadMaxBrLevel: String? = null,
        val downloadMaxbr: Int? = null,
        val fee: Int? = null,
        val fl: Int? = null,
        val flLevel: String? = null,
        val flag: Int? = null,
        val freeTrialPrivilege: FreeTrialPrivilege? = null,
        val id: Int? = null,
        val ignoreCache: String? = null,
        val maxBrLevel: String? = null,
        val maxbr: Int? = null,
        val message: String? = null,
        val payed: Int? = null,
        val pl: Int? = null,
        val plLevel: String? = null,
        val plLevels: String? = null,
        val playMaxBrLevel: String? = null,
        val playMaxbr: Int? = null,
        val preSell: Boolean? = null,
        val rightSource: Int? = null,
        val rscl: String? = null,
        val sp: Int? = null,
        val st: Int? = null,
        val subp: Int? = null,
        val toast: Boolean? = null
    ) {
        @Serializable
        data class ChargeInfo(
            val chargeMessage: String? = null,
            val chargeType: Int? = null,
            val chargeUrl: String? = null,
            val rate: Int? = null
        )

        @Serializable
        data class FreeTrialPrivilege(
            val cannotListenReason: String? = null,
            val freeLimitTagType: String? = null,
            val listenType: String? = null,
            val playReason: String? = null,
            val resConsumable: Boolean? = null,
            val userConsumable: Boolean? = null
        )
    }

    @Serializable
    data class Song(
        val a: String? = null,
        val additionalTitle: String? = null,
        val al: Al? = null,
        val alia: List<String?>? = null,
        val ar: List<Ar?>? = null,
        val awardTags: String? = null,
        val cd: String? = null,
        val cf: String? = null,
        val copyright: Int? = null,
        val cp: Int? = null,
        val crbt: String? = null,
        val displayTags: String? = null,
        val djId: Int? = null,
        val dt: Int? = null,
        val entertainmentTags: String? = null,
        val fee: Int? = null,
        val ftype: Int? = null,
        val h: H? = null,
        val hr: String? = null,
        val id: Int? = null,
        val l: L? = null,
        val m: M? = null,
        val mainTitle: String? = null,
        val mark: Long? = null,
        val mst: Int? = null,
        val mv: Int? = null,
        val name: String? = null,
        val no: Int? = null,
        val noCopyrightRcmd: String? = null,
        val originCoverType: Int? = null,
        val originSongSimpleData: OriginSongSimpleData? = null,
        val pop: Int? = null,
        val pst: Int? = null,
        val publishTime: Long? = null,
        val resourceState: Boolean? = null,
        val rt: String? = null,
        val rtUrl: String? = null,
        val rtUrls: List<String?>? = null,
        val rtype: Int? = null,
        val rurl: String? = null,
        @SerialName("s_id")
        val sId: Int? = null,
        val single: Int? = null,
        val songJumpInfo: String? = null,
        val sq: Sq? = null,
        val st: Int? = null,
        val t: Int? = null,
        val tagPicList: String? = null,
        val tns: List<String?>? = null,
        val v: Int? = null,
        val version: Int? = null
    ) {
        @Serializable
        data class Al(
            val id: Int? = null,
            val name: String? = null,
            val pic: Long? = null,
            @SerialName("pic_str")
            val picStr: String? = null,
            val picUrl: String? = null,
            val tns: List<String?>? = null
        )

        @Serializable
        data class Ar(
            val alias: List<String?>? = null,
            val id: Int? = null,
            val name: String? = null,
            val tns: List<String?>? = null
        )

        @Serializable
        data class H(
            val br: Int? = null,
            val fid: Int? = null,
            val size: Int? = null,
            val sr: Int? = null,
            val vd: Int? = null
        )

        @Serializable
        data class L(
            val br: Int? = null,
            val fid: Int? = null,
            val size: Int? = null,
            val sr: Int? = null,
            val vd: Int? = null
        )

        @Serializable
        data class M(
            val br: Int? = null,
            val fid: Int? = null,
            val size: Int? = null,
            val sr: Int? = null,
            val vd: Int? = null
        )

        @Serializable
        data class OriginSongSimpleData(
            val albumMeta: AlbumMeta? = null,
            val artists: List<Artist?>? = null,
            val name: String? = null,
            val songId: Int? = null
        ) {
            @Serializable
            data class AlbumMeta(
                val id: Int? = null,
                val name: String? = null
            )

            @Serializable
            data class Artist(
                val id: Int? = null,
                val name: String? = null
            )
        }

        @Serializable
        data class Sq(
            val br: Int? = null,
            val fid: Int? = null,
            val size: Int? = null,
            val sr: Int? = null,
            val vd: Int? = null
        )
    }
}