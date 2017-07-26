/**
 * MyClass
 *
 */
class MyClass { // Noncompliant {{There is no author inside comment}}
  MyClass(MyClass mc) { }
  
  int x() {return 0;}
  String y(int z) {return ""+z;} // comment y
}