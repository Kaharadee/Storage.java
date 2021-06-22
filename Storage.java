	 import java.awt.*;
	 import java.awt.image.*;
    /*
     * Storage.java 10.0 07 Jan 2016
     *
     * Copyright (c) School of Geography.
     * University of Leeds, Leeds, West Yorkshire, YK.LS2 9JT
     * All rights reserved.
     *
     * This code is provided under the Academic Free License v.3.0
     * For details, please see the http://opensource.org/licenses/AFL-3.0
     */


    /**
     * This class stores and converts geographical data as an image.
     * It contains a accessor method.
     * @author Dennis Macharia :dennismacharia59@yahoo.co.uk
     * @version 10.0 07 Jan 2016
     */
	public class Storage{
		 
		double[][] data = null;
		 
		public void setData(double[][] dataIn) {
			data = dataIn;
        }

		public double[][] getData(){
			return data;
        }
		 
		/**
	     * Fills the instance variable array 'data' with random numbers between 0 and 1000.
	     */
		void setRandomData(){
			if (data == null){
				data = new double [300][300];
			} else {
				data = new double[data.length][data[0].length];
					for (int i = 0; i < data.length; i++) {
						for (int j = 0; j < data[i].length; j++) {
							double var = Math.random() * 1000.0;
							data[i][j] = var;
						}
					}
			
			}
		}
			
		 /**
		  * Prints the array.
		  */
		 void printArray (double[][] a){
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					System.out.print(a[i][j] + " ");
				}
				System.out.println("/n");
			}		
		 }
		 
		 /**
		   *compares the data in the array and gets the maximum 
		   *@return maximum
		   */
		 double getMaximum (){
			double maximum = -1;
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[i].length; j++) {
						if (data[i][j]> maximum) {
							maximum =data[i][j];
						}
					}
				} 
			return maximum;
			
		 }
		 	
		 /**
		  *compares the data in the array and gets the minimum then returns the minimum
		  *@return minimum
		  */
		 double getMinimum (){
			double minimum = data[0][0];
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[i].length; j++) {
						if (data[i][j] < minimum) {
							minimum =data[i][j];
						}
					}
				}
			return minimum;
		 }
		
		/**
		 *Creates a new stretched array
		 *@param newMinimum double
		 *@param newMaximum double
		 *@return tempArray
		 */	
		double[][] getRerangedData(double newMinimum, double newMaximum){
		// Set up double variables containing currentMaximum and currentMinimum (where could we get these from...?)
			double currentMaximum = getMaximum();
			double currentMinimum = getMinimum();
		// Make a double[][] array called tempArray, and size [data.length][data[0].length]
			double[][] tempArray = new double[data.length][data[0].length];
				for (int i = 0; i < data.length; i++){
					for (int j = 0; j < data[i].length; j++){
						tempArray[i][j]=data [i][j];
						tempArray[i][j] = tempArray[i][j] - currentMinimum;
						tempArray[i][j] = tempArray[i][j] / (currentMaximum - currentMinimum);
						tempArray[i][j] = tempArray[i][j] * (newMaximum - newMinimum);
						tempArray[i][j] = tempArray[i][j] + newMinimum;
					}
				}
			return tempArray;
		}
		
		/**
		 *Resizes the array from 2D to 1D
		 *@return tempArray
		 */
		double [] get1DArray(){
			double[][] reranged = getRerangedData(0.0,255.0);
			double[] tempArray = new double[reranged.length * reranged[0].length];
				for (int i = 0; i < data.length; i++){
					for (int j = 0; j < data[i].length; j++){
						tempArray[(reranged[0].length * i)+ j] = reranged[i][j];
					}	
				}
			return tempArray;
		}
	    
		
		public Image getDataAsImage() {
			if (data != null){
				double[] tempArray = get1DArray();
				int []pixels = new int[tempArray.length];
					for (int i=0; i< pixels.length; i++){
					int value = (int) tempArray[i];
					Color pixel = new Color (value,value,100);
					pixels [i] = pixel.getRGB();
					
					}
				MemoryImageSource memImage = new MemoryImageSource (data[0].length,data.length,pixels,0,data[0].length);
				Panel panel = new Panel();
				Image image = panel.createImage(memImage);
				
				return image;
			} else { 
				return null;
		    }	
		}

 
	 
	}	