import java.util.ArrayList;

public class ArrayManager {
	// Your instance variables here
	int numFrames;
	int frameSize;
	int[] data;
	ArrayList<Integer> FreeFrameList; 
	int c1 =0;
	int sF = 0;
	double currentFrames;
	public ArrayManager(int numFrames, int frameSize) {
		this.numFrames = numFrames;
		this.frameSize = frameSize;
		FreeFrameList = new ArrayList<Integer>();
		data = new int[numFrames];
		for (int i = 0; i < numFrames ; i++){
			FreeFrameList.add(i,0);
		}		
	}
	public Array createArray(int size) throws OutOfMemoryException {		
		int rps	= 0;
		
			double usedFrames = Double.valueOf(size)/Double.valueOf(frameSize);
		double currentFrames = (int)Math.ceil(usedFrames);
		if(currentFrames>FreeFrameList.size()){
			throw new OutOfMemoryException("Out of Memory: Cannot create array of size " + size + " with " + FreeFrameList.size() + " of " + frameSize + " available");
		}
	        for(int i = 0; i < numFrames; i++){
			data[i] = i;
			//FreeFrameList.set(i,1);	
		}
		for(int i = 0; i < sF; i++){
			rps++;
			}
		PagedArray pA = new PagedArray(data, size);
		pA.setPageTable(data);
		pA.getPageTable();	
		pA.toString();
		return pA;
	}
	public Array aliasArray(Array a) {
		PagedArray pA = (PagedArray)a;
		return pA;
	}
	public void deleteArray(Array a) {
		PagedArray pA = (PagedArray)a;
		int in =0;
		for (int i = 0; i < pA.PageTable.length; i++){
			if(pA.PageTable[i] != 0){
			   pA.PageTable[i] = 0; 
			   FreeFrameList.set(i,0);
			}
			else{
			   in++;
			}
		}
		for(int i = sF; i < sF +in; i++){
				FreeFrameList.set(i,0);
			}
	}
	public void resizeArray(Array a, int newSize) throws OutOfMemoryException {	
	/* Broken
	PagedArray pa = (PagedArray)a;
	
	int[] b = new int[newSize];

	int aLength=a.length;
	if (newSize >  aLength){
		if(FreeFrameList.size < (newSize/frameSize)){
			throw new OutOfMemoryExpection("Not enough frames for the new page table\n");
		}
		else{
			int c=(newSize/frameSize)-pa.PagedArray.size;
			pa.PagedArray[i]=pa.PagedArray.size[i]+c; 
			for(int i=0;i<newSize;i++){
				b[i] = a[i];
			}
		}
	}
	if  (newSize ==  aLength){
		for(int i =0;i<newSize;i++){
			b[i]=a[i];
		}
	}
	else{
		int c=pa.PagedArray.length-(newSize/frameSize);
		pa.PagedArray[i]=pa.PagedArray.size[i]+c;
		for(int i=0;i<newSize;i++){
			b[i]=a[i];
		}
   	}
}*/}
	public void printMemory() {
		double math;
		math =  Double.valueOf(c1)/Double.valueOf(numFrames);
		System.out.format("Memory %d x %d %.2f%% full \n", numFrames, frameSize, math*100.00);
		for(int i = 0; i < numFrames; i++){
			if(FreeFrameList.get(i) != 0){
			System.out.print("X");
			}
			else{
				System.out.print("-");
			}
		}
		System.out.println();
		}
	private class PagedArray implements Array {
		// Your instance variables here
		int[] PageTable;
		int[] table;
		int length =0;
		int sF = 0;

		public PagedArray(int pageTable[], int length) {
			// Your code here
			this.length = length;	
			double currentFrames = Double.valueOf(length)/Double.valueOf(frameSize);
			table = new int[length];
			sF = (int)Math.ceil(currentFrames);
			Array[] list = new Array[sF];
			PageTable = new int[sF];		
		}

		public int getValue(int index) throws SegmentationViolationException {
			int numSet = length -1;
		 	if(index < 0 || index > numSet){			
				throw new SegmentationViolationException("Index "+ index +" is out of range. Expected "+ 0 + "->"+ numSet);
			}	
			return table[index];
		}

		public void setValue(int index, int val) throws SegmentationViolationException {
			int numSet = length -1;
			if(index < 0 || index > numSet){			
				throw new SegmentationViolationException("Index "+ index +" is out of range. Expected "+ 0 + "->"+ numSet);
			}	
			table[index] = val;
		}
		public String toString() {
			String pageIndex = "Length: "+ length +"@ ";
			int cl = 0;	
			for(int i = 0; i < sF; i++){	
				if(PageTable[i] != 0){
				pageIndex += i + " "; 
				} 
				else{
				cl++;
				}
			}
			for(int i = sF; i < cl + sF; i++){
				pageIndex += i + " ";
			}		
			return pageIndex;
		}
		public int[] getPageTable() {
			int[] list;
			return PageTable;
		}
		public void setPageTable(int[] pageTable) {
			int c3	= 0;
			for (int i = c3; i < sF; i++){
				PageTable[i] = FreeFrameList.get(i);
			}
			for(int i = 0; i < sF; i++){
				c3++;
			}			
		}
		public int length() {
			return length;
		}
		
		public void setLength(int length) {
			this.length = length;
		}
	}
}
