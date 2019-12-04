package com.wen.leetcode.medium;

import org.junit.Test;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author wen
 * @date 2019-11-22 14:45
 */
public class TwoAdd {

    @Test
    public void test() {
        ListNode l1 = new ListNode(0);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;

        ListNode l2 = new ListNode(0);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;

        ListNode result = addTwoNumbers2(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    /**
     * 思路：
     *      两个链表各取一个相加，然后放到离头指针近的位置，然后依次向后添加。
     *      需要注意的就是两个值相加会大于10，需要进位。
     *
     * 首先这个思路比较好想，那个sum即用来作为运算结果，也用来做进位使用。
     * 比较失败的一点是在于处理sum与9或10之间的关系了，提交了三次，都失败了。
     * @param l1 l1
     * @param l2 l2
     * @return 结果
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rear = null;
        ListNode result = null;
        int sum = 0;
        while ((l1 != null || l2 != null) || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            int num = sum;
            if (sum > 9) {
                num = sum % 10;
                sum /= 10;
            } else {
                sum = 0;
            }
            if (rear == null) {
                rear = new ListNode(num);
                result = rear;
            } else {
                rear.next = new ListNode(num);
                rear = rear.next;
            }
        }
        return result;
    }

    /**
     * 思路：
     *      把值全部加起来，然后一个一个分离，放到链表中。
     *
     * 该方法果然不行。会有一些边界值得情况。
     * 最开始想的就是这种方法，感觉不太好，没啥挑战的，就用了上边的先解问题了。
     * 没想到准备用这种方法解一次的时候，确实有好多坑，填不平。还是用上边那种把。
     * @param l1 l1
     * @param l2 l2
     * @return 结果
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int i = 1;
        int sum = 0;
        while (l1 != null) {
            sum += (l1.val * i);
            i *= 10;
            l1 = l1.next;
        }
        i = 1;
        while (l2 != null) {
            sum += (l2.val * i);
            i *= 10;
            l2 = l2.next;
        }

        ListNode result = null;
        ListNode rear = null;

        //如果0 + 0会有问题
        while (sum > 0 ) {
            if (rear == null) {
                rear = new ListNode(sum % 10);
                result = rear;
            } else {
                rear.next = new ListNode(sum % 10);
                rear = rear.next;
            }
            sum /= 10;
        }
        return result;
    }

    /**
     * 官方解法
     * @param l1 l1
     * @param l2 l2
     * @return 结果
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
