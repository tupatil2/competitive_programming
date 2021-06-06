void mergeSort(int[] nums, int start, int end){
    if(start == end) return;
    int mid = start + (end-start)/2;
    // divide
    mergeSort(nums,start,mid);
    mergeSort(nums,mid+1,end);
    // conquer
    merge(nums,start,mid,end);
}
    
void merge(int[] nums, int start, int mid, int end){
    int l = start, r = mid+1;
    int[] temp = new int[end-start+1];
    int index = 0;
    while(l <= mid && r <= end){
        if(nums[l] <= nums[r]){
            temp[index++] = nums[l];
            l++;
        }
        else {
            temp[index++] = nums[r];
            r++;
        }
    }
    while(l <= mid){
        temp[index++] = nums[l++];
    }
    while(r <= end){
        temp[index++] = nums[r++];
    }
    for(int i = start; i <= end; i++){
        nums[i] = temp[i-start];
    }
}
