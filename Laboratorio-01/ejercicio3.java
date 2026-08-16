public static void main(String[]args) {
      Random random=new Random();
  int[] numeros = new int[20000];

      for(int i=0;i<20000;i++) {
            int numero=random.nextInt(6)+1;
            numeros[i] = numero;
      }
    for (int j = 0; j < numeros.length; j++) {
      System.out.println(numeros[j]);
    }
}

