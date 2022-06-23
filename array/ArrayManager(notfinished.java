import java.util.ArrayList;

public class ArrayManager {
	// Your instance variables here
	int numFrames;
	int frameSize;
	int[] data; 
	ArrayList<Integer> FreeFrameList;
	ArrayList<Integer> TakenFrames;
	public ArrayManager(int numFrames, int frameSize) {
		this.numFrames = numFrames;
		this.frameSize = frameSize;
		ArrayList<Integer> FreeFrameList = new ArrayList<Integer>();
		ArrayList<Integer> TakenFrames = new ArrayList<Integer>();
		data = new int[numFrames*frameSize];
		for (int i = 0; i < numFrames ; i++){
			FreeFrameList.add(i);
		}
		for(int i = 0; i < data.length(); i++){
			data[i]=0;
		}
			
	}
	public Array createArray (int size) throws OutOfMemoryException {
		double currentFrames = size/framSize;
		double usedFrames = (int)Math.ceil(currentFrames);
		int Parray = new int[usedFrames];
		int value = (int) usedFrames;
		for(int i = 0; i < size; i ++){
			data[i] = i;
		}

		if(usedFrames > FreeFrameList.size()){
			throw new OutOfMemoryException("Out of Memory: Cannot create array of size "+size+" with "+FreeFrameList.size()+" frames of "+frameSize+" availableTo big of a array, not enough frames\n");
		}
		for(int i = 0; i < usedFrames; i++){
       		Parray[i] = FreeFrameList.get(0);
		TakenFrames.add(i,FreeFrameList.get(0));
		FreeFramesList.remove(0);
		}	
		PageArray pa = new PagedArray(Parray,size);
		pa.setPageTable();
		pa.toString();
		
		return null;
	}
	public Array aliasArray(Array a) {
		PagedArray pa = (PagedArray)a;
		return pa;

	}

	public void deleteArray(Array a) {
	

	}

	public void resizeArray(Array a, int newSize) throws OutOfMemoryException {		
		
	}

	public void printMemory() {
		int repeat=0;
		double math=0;
		math =  repeat/NumFrames;
		System.out.format("Memory %d x %d %.2f%% full \n", numFrames, frameSize, math*100.00);
		for(int i = 0; i < numFrames; i++){ 
			if(i==FreeFrameList.get(repeat)){
			System.out.print("-");
			}
			else{
				System.out.print("X");
			}
		System.out.printf("\n");
		}
	}
}
	private class PagedArray implements Array {
		// Your instance variables here
		int[] PageTable;
		int length;

		public PagedArray(int pageTable[], int length) {
			// Your code here
			this.length = length;
			this.pageTable = pageTable;	
					
		}

		public int getValue(int index) throws SegmentationViolationException {
			// Your code here
			int max_range = length -1;
		 	if(index < 0 || index > max_range){			
	throw new SegmentationViolationException("Index "+ index +" is out of range. Expected "+ 0 + "->"+ max_range);
}	
			return table[index];
		}

		public void setValue(int index, int val) throws SegmentationViolationException {
			int max_range = length -1;
			if(index < 0 || index > max_range){			
	throw new SegmentationViolationException("Index "+ index +" is out of range. Expected "+ 0 + "->"+ max_range);
}	
			table[index] = val;
		}

		public String toString() {
			// Your code her
			String p_index = "Length"+length+"@";
			for(int i = 0; i < pageTable.length;i++){
				p_index += pageTable[i] + " ";
			}
			return null;
		}

		public int[] getPageTable() {
			return PageTable;
		}
		
		public void setPageTable(int[] pageTable) {
			
			
		}
		
		public int length() {
			return pageTable.length;
		}
		
		public void setLength(int length) {
			//pageTable.length = length;
		}
	}

