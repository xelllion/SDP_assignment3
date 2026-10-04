# Assignment 3 | Bridge Pattern

**Student:** Aruzhan Amerkhanova  
**Group:** SE-2529  
**Topic:** A — Drawing  
**Repository:** https://github.com/xelllion/SDP_assignment3.git  
**Base commit:** b5ba7939358465442970081945249204b5f633c5

## Overview

This project demonstrates the Bridge design pattern.

There are two independently varying dimensions:

1. Shapes:
    - Circle
    - Square

2. Rendering implementations:
    - VectorRenderer
    - RasterRenderer
    - AsciiRenderer

The `Shape` abstraction stores a reference of type `Renderer`.
This allows the rendering implementation to change independently
from the shape hierarchy.

## Role Map

| Bridge Role | Class | Source |
|---|---|---|
| Abstraction | Shape | `src/Shape.java` |
| A1 | Circle | `src/Circle.java` |
| A2 | Square | `src/Square.java` |
| Implementor | Renderer | `src/Renderer.java` |
| I1 | VectorRenderer | `src/VectorRenderer.java` |
| I2 | RasterRenderer | `src/RasterRenderer.java` |
| I3 | AsciiRenderer | `src/AsciiRenderer.java` |
| Client | Main | `src/Main.java` |

## Important Code Locations

The interface-typed bridge field is located in:

`src/Shape.java`

```java
protected Renderer renderer;