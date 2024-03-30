// generic templ for two pointers algo
class Solution {
  public int countAtKDistinct(int[] nums, int k) {
    int n = nums.length;
    int l = 0;
    int r = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int count = 0;
    while (r < n) {
      map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
      while (map.size() > k) {
        map.put(nums[l], map.get(nums[l]) - 1);
        if (map.get(nums[l]) == 0) {
          map.remove(nums[l]);
        }
        l++;
      }
      count += (r - l + 1);
      r++;
    }
    return count;
  }
}
