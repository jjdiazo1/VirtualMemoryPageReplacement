# VirtualMemoryPageReplacement

Este proyecto simula la administración de memoria virtual utilizando un algoritmo de reemplazo de páginas. Está diseñado para ayudar a entender cómo varía el comportamiento de un proceso según la memoria asignada, utilizando un proceso de **esteganografía** en imágenes para esconder y recuperar mensajes.

## Ejemplo
Esconder en la *Imagen 1* el mensaje de la *Imagen 2*, el resultado se ve en la *Imagen 3*.

<img width="506" alt="Screenshot 2024-10-11 at 11 40 28 AM" src="https://github.com/user-attachments/assets/b0b2c7bb-a001-4a8e-9b7f-705c9e014f91">

↑ *Imagen 1*

<img width="506" alt="Screenshot 2024-10-11 at 11 40 47 AM" src="https://github.com/user-attachments/assets/28b626e7-52e2-4ac7-ac09-d20e3a24c226">

↑ *Imagen 2*

<img width="506" alt="Screenshot 2024-10-11 at 11 40 24 AM" src="https://github.com/user-attachments/assets/9984f62c-09ef-4852-91ca-3b3b63b3b417">

↑ *Imagen 3*

## Características

- Implementación del algoritmo "No Usadas Recientemente" para el reemplazo de páginas.
- Simulación del sistema de administración de memoria con cálculo de hits y fallas de página.
- Soporte para múltiples tamaños de imagen, mensajes escondidos y diferentes configuraciones de marcos de página.
- Multithreading para actualizar los estados de la tabla de páginas y el bit de referencia.

## Requisitos

- Java SE 17 o superior
- Entorno de desarrollo compatible (Eclipse, IntelliJ, VS Code, etc.)

## Estructura del Proyecto

```
VirtualMemoryPageReplacementSimulator/
    ├── src/
    │     └── caso2_jj.diazo1_rpazl/
    │           ├── HiloActualizadorR.java
    │           ├── HiloProcesador.java
    │           ├── Imagen.java
    │           ├── Main.java
    │           ├── Opcion1.java
    │           ├── Opcion2.java
    │           ├── Pagina.java
    │           ├── Referencia.java
    │           ├── SistemaPaginacion.java
    │           └── Utilidades.java
    ├── Archivos/
    │     ├── caso2-mensaje_dollshousep1.txt
    │     ├── caso2-parrots_mod.bmp
    │     └── caso2-parrots.bmp
    └── README.md
```

## Instrucciones de Uso

### Clonar el Repositorio

Clona este repositorio a tu máquina local:

```sh
git clone https://github.com/tuusuario/VirtualMemoryPageReplacementSimulator.git
```

### Compilar el Proyecto

Puedes compilar el proyecto desde la línea de comandos o utilizando un entorno de desarrollo como Eclipse.

### Ejecutar el Proyecto

Para ejecutar el proyecto, usa el siguiente comando desde la carpeta `src`:

```sh
java caso2_jj.diazo1_rpazl.Main
```

### Opciones del Menú

El programa presenta las siguientes opciones:

1. **Generar Referencias**: Recibe el tamaño de página y el nombre del archivo de la imagen con el mensaje escondido. Genera un archivo con las referencias de página.
2. **Calcular Datos Buscados**: Recibe el número de marcos de página y el archivo de referencias. Calcula los hits, fallas y tiempos de acceso a la memoria.
3. **Esconder un Mensaje en una Imagen**: Utiliza la técnica de esteganografía para esconder un mensaje en la imagen.
4. **Recuperar un Mensaje de una Imagen**: Recupera un mensaje escondido previamente en una imagen.
5. **Salir**: Finaliza la ejecución del programa.

### Archivos de Entrada

Los archivos de entrada necesarios están ubicados en la carpeta `Archivos/` y deben ser referenciados por su ruta completa o relativa (`src/archivos/nombreArchivo`).

