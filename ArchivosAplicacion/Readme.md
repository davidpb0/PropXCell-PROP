# Subgrup Prop 1.4

- David Pérez
- Iván Risueño
- Joaquim Torra

# Convención de Código 

---

### Comentando

**Comentarios iniciales**

Todos los ficheros empezarán con estos comentarios:
```java
/* 
 * ClassName
 * 
 * Version info
 * 
 * Author
 */
```

**Documentación**

Para documentar una función, utilizaremos las directrices `@param`, `@return` y `@see`.

### Declaración

Se recomienda una declaración por línea para dejar espacio para poner comentarios.
Se prefiere:
```java
int level; // indentation level
int size;  // size of table
```
ante:
```java
int level, size;
```

En NINGÚN caso declararemos una variable y una función, ni dos variables de distinto tipo en una misma línea.
```java
long dbaddr, getDbaddr(); // MAL!!
int foo, fooArray[]; // MAL!!
```

En funciones, declararemos las variables al inicio del bloque en el que se van a utilizar:
```java
void MyMethod() {
    int int1; // beginning of method block
    if (condition) {
        int int2; // beginning of "if" block
        ...
    }
}
```

### Nombrar

**Classes:** Los nombres de classes son nombres, *mixed case*, es decir, 
con la primera letra de cada palabra interna en mayúscula. 
```java
class Raster;
class ImageSprite;
```

**Funciones:** Las funciones siempre serán verbos, con *snake case*:
```java
public void run();
public void runFast();
public int getNumber();
```

**Variables:** Las variables tendrán nombres cortos y entendibles, con *snake case*:
```java
int i;
char cp;
float myWidth;
```

**Constantes:** Las constantes las nombraremos con *upper snake case*:
```java
int MIN_WIDTH = 4;
int MAX_WIDTH = 999;
int GET_THE_CPU = 1;
```

*Los parámetros de una función siempre los declararemos con una `_` delante:*
```java
public void setNewValue(String _value);
```