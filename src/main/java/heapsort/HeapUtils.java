package heapsort;

public class HeapUtils {
    public static int findParentIndex(int[] data, int pos){
        if(data == null || pos >= data.length){
            throw new IllegalArgumentException();
        }

        if(pos % 2 ==0){
            return pos/2 - 1;
        }
        return pos/2;
    }

    public static int findParent(int[] data, int pos){
        return data[findParentIndex(data,pos)];
    }

    public static int findLeftChildIndex(int[] data, int pos){
        if(data == null || pos >= data.length){
            throw new IllegalArgumentException();
        }
        int index = pos * 2 +1;
        if(index < data.length){
            return index;
        }
        throw new IllegalArgumentException();
    }

    public static int findLeftChild(int[] data, int pos){
        return data[findLeftChildIndex(data,pos)];
    }

    public static int findRightChildIndex(int[] data, int pos){
        if(data == null || pos >= data.length){
            throw new IllegalArgumentException();
        }
        int index = (pos + 1) * 2;
        if(index < data.length){
            return index;
        }
        throw new IllegalArgumentException();
    }

    public static int findRightChild(int[] data, int pos){
        return data[findRightChildIndex(data,pos)];
    }

    public static boolean leaf(int[] data, int pos){
        if(data == null || pos >= data.length){
            throw new IllegalArgumentException();
        }
        int index1 = (pos + 1) * 2;
        int index2 = pos * 2 + 1;
        if(index1 >= data.length && index2 >= data.length){
            return true;
        }
        return false;
    }

    /**
     * 初始化大根堆
     * */
    public static void init(int[] data) {
        if(data == null || data.length == 1)return;
        int temp = 0;
        for (int i = data.length -1; i >= 0; i--) {
            if(!HeapUtils.leaf(data, i) && i != 0){
                int parent = HeapUtils.findParent(data, i);
                int parentIndex = HeapUtils.findParentIndex(data, i);
                if(data[i] > parent){
                    temp = data[i];
                    data[i] = parent;
                    data[parentIndex] = temp;
                }
            }
        }
    }


    /**
     * 大根堆堆顶元素被取出后，重新恢复平衡
     * @param data 待排序数组
     * @param nowPos 被交换元素的位置
     * @param endPos 排序范围终点
     */
    public static void rebalance(int[] data,int nowPos, int endPos){
        if(HeapUtils.leaf(data,nowPos))return;
        int leftIndex = nowPos * 2 + 1;
        if(leftIndex >= endPos)return;
        int rightIndex = (nowPos + 1) * 2;
        if(rightIndex >= endPos){//如果只有左子树
            if(data[leftIndex] > data[nowPos]){
                int temp = data[leftIndex];
                data[leftIndex] = data[nowPos];
                data[nowPos] = temp;
                rebalance(data,leftIndex,endPos);
            }
        }else {//如果左右子树都有
            int max = data[leftIndex] > data[rightIndex] ? data[leftIndex]:data[rightIndex];
            int maxIndex = data[leftIndex] > data[rightIndex] ? leftIndex:rightIndex;
            if(max > data[nowPos]){
                int temp = data[nowPos];
                data[nowPos] = data[maxIndex];
                data[maxIndex] = temp;
                rebalance(data,maxIndex,endPos);
            }
        }

    }

    /**
     * 堆排序
     */
    public static void sort(int[] data){
        //堆初始化为大根堆
        init(data);

        int temp = 0;
        for (int i = 0; i < data.length - 1; i++) {
            //交换堆顶元素
            temp = data[0];
            data[0] = data[data.length - i - 1];
            data[data.length - i - 1] = temp;

            //堆调整
            rebalance(data,0, data.length - i - 1);
        }
    }
}
