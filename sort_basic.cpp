#include <cstdio>

void swap(int &a,int &b){
    int temp = a;
    a = b;
    b = temp;
}

// O(n^2)
void SelectionSort(int arr[] , int n){
    for(int i = 0; i < n; i++){
        int minIndex = i;
        for(int j = i+1; j < n; j++)
            if(arr[j] < arr[minIndex])
                minIndex = j;
        swap(arr[i],arr[minIndex]); 
    }
}

// O(n^2) , NearlyOreadArray -> the most best improve to O(n)
void InsertionSort(int arr[] , int n){
	for(int i = 1; i < n; i++){
		int e = arr[i];
		int j;
		for(j = i; j > 0 && e < arr[j-1]; j--)
			arr[j] = arr[j-1];
		arr[j] = e;
	}
}

// func: Print array info
void PrintInfo(int arr[],int n){
    for(int i = 0; i < n; i++)
        printf("%d ",arr[i]);
    printf("\n");
}


int main(){
    int arr[10] = {3,5,1,4,6,2,8,9,7,0};
    PrintInfo(arr,10);
    InsertionSort(arr,10);
    PrintInfo(arr,10);
    return 0;
}



