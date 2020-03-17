package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2020-01-21
 */
public class BestTimeToBuyAndSellStockWithFransactionFee {

    @Test
    public void test() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit2(prices, fee));
    }

    public int maxProfit(int[] prices, int fee) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        int[] dp1 = new int[length];
        int[] dp2 = new int[length];
        dp1[0] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp1[i] = Math.max(dp1[i-1], dp2[i-1] - prices[i]);
            dp2[i] = Math.max(dp2[i-1], dp1[i-1] + prices[i] - fee);
        }
        return dp2[length-1];
    }

    public int maxProfit2(int[] prices, int fee) {
        //第i天不持有股票的收益
        int cash = 0;
        //第i天持有股票的收益
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //第i天不持有股票或者将持有的股票卖出
            cash = Math.max(cash, hold + prices[i] - fee);
            //第i天继续持有股票或重新买入股票
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

}
