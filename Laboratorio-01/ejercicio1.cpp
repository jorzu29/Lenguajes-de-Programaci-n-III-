#include <iostream>
using namespace std;

void sumarArreglo(int arreglo[], int tamano) {
    int suma = 0;
    
    for (int i = 0; i < tamano; i++) {
        suma += arreglo[i];
    }
    
    cout << "La suma del arreglo da " << suma << endl;
}

int main() {
    int arreglo[] = {1, 2, 3, 4, 100};
    
    int tamano = sizeof(arreglo) / sizeof(arreglo[0]); 
    
    sumarArreglo(arreglo, tamano);
}
