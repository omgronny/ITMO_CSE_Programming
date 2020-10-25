class Lab {
	public static void main(String[] args) {
	
		int[] d = new int[12];
		for (int i = 0; i <= 11; i++) {
			d[i] = i + 6;
		}

		float[] x = new float[20];
		for (int i = 0; i < 20; i++){
			x[i] = (float) (Math.random()*15.0 - 5.0);
		}


		double[][] b = new double[12][20];

		for (int i = 0; i < 12; i++){
			for (int j = 0; j < 20; j++){

				if (d[i] == 7) {

					b[i][j] = Math.pow(Math.asin((x[j]+2.5)/60.0), 1/3);

				} else if (d[i] ==  6 || d[i] ==  10 || d[i] ==  12 || d[i] ==  13 || d[i] ==  14 || d[i] ==  16){

					b[i][j] = Math.cos(Math.pow(Math.sin(x[j]), 1/3));

				} else {

					b[i][j] = Math.sin(Math.asin(Math.sin(x[j])));
				}


			}

		}

		for (int i = 0; i < 12; i++){
			for (int j = 0; j < 20; j++){
				if (b[i][j] >= 0){
					System.out.print(" ");
					System.out.printf("%.2f", b[i][j]);
					System.out.print(" ");
				} else {
					System.out.printf("%.2f", b[i][j]);
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
		

	}
}
