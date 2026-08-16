def sumarArreglo (arreglo):
    suma = 0;
    
    for i in range(0, len(arreglo)):
        suma += arreglo[i]
    print(f"La suma total del arreglo es: {suma}")

arreglo = [1,2,3,4,100];
sumarArreglo(arreglo)
