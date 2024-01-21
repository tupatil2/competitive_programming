// helps to find the median/kth sum in a range l, r in logn
// This class is finding the smallest sum of k elements in rolling range of l to r
class RollingElement {
      int leftSize;
      TreeSet<Integer> left; // index
      TreeSet<Integer> right;
      long sum;
      int[] nums;
      public RollingElement(int[] nums, int k) {
          this.leftSize = k;
          this.left = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
          this.right = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
          this.sum = 0L;
          this.nums = nums;
      }

      private void balance() {
          while(left.size() < leftSize && !right.isEmpty()) {
              sum += nums[right.first()];
              left.add(right.first());
              right.remove(right.first());
          }
          while(left.size() > leftSize) {
              sum -= nums[left.last()];
              right.add(left.last());
              left.remove(left.last());
          }
      }   

      public void add(int index) {
          if(left.size() == 0 || nums[index] < nums[left.last()]){
              left.add(index);
              sum += nums[index];
          } else {
              right.add(index);
          } 
          balance();
      }

      public void remove(int index) {
          if(right.contains(index)) {
              right.remove(index);
          } else {
              left.remove(index);
              sum -= nums[index];
          }
          balance();
      }

      public long getSum() {
          return this.sum;
      }
  }
}

// problems related to this concept: https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/description/
