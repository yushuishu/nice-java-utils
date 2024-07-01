package com.shuishu.utils.tool.math;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 9:48
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：数值操作
 * <p></p>
 * 参考：Add, subtract, multiply and divide
 */
public class NiceNumber {

    /**
     * 除法
     *
     * @param numerator    除数
     * @param denominator  被除数
     * @param scale        保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode 舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Double divideOfDouble(Integer numerator, Integer denominator, int scale, RoundingMode roundingMode) {
        if (numerator == null || numerator == 0) {
            return 0d;
        }
        if (denominator == null) {
            return null;
        }
        return divideOfDouble(new BigDecimal(numerator), new BigDecimal(denominator), scale, roundingMode);
    }

    /**
     * 除法
     *
     * @param numerator    除数
     * @param denominator  被除数
     * @param scale        保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode 舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Double divideOfDouble(Long numerator, Long denominator, int scale, RoundingMode roundingMode) {
        if (numerator == null || numerator == 0) {
            return 0d;
        }
        if (denominator == null) {
            return null;
        }
        BigDecimal numeratorDecimal = new BigDecimal(numerator);
        BigDecimal denominatorDecimal = new BigDecimal(denominator);
        return numeratorDecimal.divide(denominatorDecimal, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 除法
     *
     * @param numeratorDecimal   除数
     * @param denominatorDecimal 被除数
     * @param scale              保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode       舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Double divideOfDouble(@NotNull(message = "除数不能为空") BigDecimal numeratorDecimal,
                         @NotNull(message = "被除数不能为空") BigDecimal denominatorDecimal,
                         int scale,
                         RoundingMode roundingMode) {
        // 默认保留小数点位数为 0
        scale = Math.max(scale, 0);
        // 默认处理小数为 四舍五入
        roundingMode = roundingMode == null ? RoundingMode.HALF_UP : roundingMode;
        return numeratorDecimal.divide(denominatorDecimal, scale, roundingMode).doubleValue();
    }


    /**
     * 除法
     *
     * @param numerator    除数
     * @param denominator  被除数
     * @param scale        保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode 舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Float divideOfFloat(Integer numerator, Integer denominator, int scale, RoundingMode roundingMode) {
        if (numerator == null || numerator == 0) {
            return 0f;
        }
        if (denominator == null) {
            return null;
        }
        return divideOfFloat(new BigDecimal(numerator), new BigDecimal(denominator), scale, roundingMode);
    }

    /**
     * 除法
     *
     * @param numerator    除数
     * @param denominator  被除数
     * @param scale        保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode 舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Float divideOfFloat(Long numerator, Long denominator, int scale, RoundingMode roundingMode) {
        if (numerator == null || numerator == 0) {
            return 0f;
        }
        if (denominator == null) {
            return null;
        }
        return divideOfFloat(new BigDecimal(numerator), new BigDecimal(denominator), scale, roundingMode);
    }

    /**
     * 除法
     *
     * @param numeratorDecimal   除数
     * @param denominatorDecimal 被除数
     * @param scale              保留小数点多少位，默认0位，即不保留小数位
     * @param roundingMode       舍入模式，默认是 四舍五入（RoundingMode.HALF_UP）
     * @return -
     */
    public static Float divideOfFloat(@NotNull(message = "除数不能为空") BigDecimal numeratorDecimal,
                                 @NotNull(message = "被除数不能为空") BigDecimal denominatorDecimal,
                                 int scale,
                                 RoundingMode roundingMode) {
        // 默认保留小数点位数为 0
        scale = Math.max(scale, 0);
        // 默认处理小数为 四舍五入
        roundingMode = roundingMode == null ? RoundingMode.HALF_UP : roundingMode;
        return numeratorDecimal.divide(denominatorDecimal, scale, roundingMode).floatValue();
    }


}
