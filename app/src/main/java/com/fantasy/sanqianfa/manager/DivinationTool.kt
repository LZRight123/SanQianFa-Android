package com.fantasy.sanqianfa.manager

import com.fantasy.sanqianfa.components.Hexagram

/**
 * 爻的类型
 * @property value 对应的二进制值（0为阴，1为阳）
 * @property faceCount 钱币正面的数量
 */
enum class YaoType(val value: String, val faceCount: Int = -1) {
    老阳(value = "1", faceCount = 3),    //  (3个正面，变为少阴)
    少阳(value = "1", faceCount = 2), //  (2个正面)
    少阴(value = "0", faceCount = 1),  //  (1个正面)
    老阴(value = "0", faceCount = 0),      //  (0个正面，变为少阳)
    未定(value = "0", faceCount = -1),
    ;
    val change: YaoType get() = when (this) {
        老阳 -> 少阴
        少阳 -> 少阳
        少阴 -> 少阴
        老阴 -> 少阳
        未定 -> 未定
    }
}

object DivinationTool {
    /**
     * 根据二进制字符串获取卦象
     * @param binary 6位二进制字符串，1代表阳，0代表阴
     * @return 对应的卦象，如果找不到返回null
     */
    fun getHexagramFromBinary(binary: String): Hexagram? {
        return Hexagram.fromBinary(binary)
    }

    /**
     * 根据每爻的正面数量获取爻的类型
     * @param upCount 正面朝上的数量 (0-3)
     * @return 对应的爻类型
     */
    fun getYaoType(upCount: Int): YaoType {
        return when (upCount) {
            3 -> YaoType.老阳
            2 -> YaoType.少阳
            1 -> YaoType.少阴
            0 -> YaoType.老阴
            else -> YaoType.未定
        }
    }
    
    /**
     * 根据爻的类型数组获取卦象
     * @param yaoTypes 从下到上六个爻的类型列表
     * @return 对应的卦象
     */
    fun getOriginalHexagram(yaoTypes: List<YaoType>): Hexagram? {
        if (yaoTypes.size != 6) {
            throw IllegalArgumentException("Must provide exactly 6 yao types")
        }
        
        val binary = yaoTypes.joinToString("") { it.value }

        return getHexagramFromBinary(binary)
    }

    /**
     * 获取变卦
     * @param yaoTypes 从下到上六个爻的类型列表
     * @return 变卦后的卦象
     */
    fun getChangedHexagram(yaoTypes: List<YaoType>): Hexagram? {
        if (yaoTypes.size != 6) {
            throw IllegalArgumentException("Must provide exactly 6 yao types")
        }
        
        val changedBinary = yaoTypes.joinToString("") { yaoType ->
            yaoType.change.value
        }

        return getHexagramFromBinary(changedBinary)
    }

    /**
     * 检查是否有变爻
     * @param yaoTypes 从下到上六个爻的类型列表
     * @return 是否存在变爻
     */
    fun hasChangingLines(yaoTypes: List<YaoType>): Boolean {
        return yaoTypes.any { it == YaoType.老阳 || it == YaoType.老阴 }
    }
}