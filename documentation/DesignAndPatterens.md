# Design and Patterens

## Design:
SOLID Principles 
1. S - Single Responsibility 
    - make sure your classes only have 1 responsibility and 1 reason to change

2. O - Open/closed principles 
    - When implementing objects, you must not change the behvour of the object, but only extent. \
    Example: You have a object called "Shape". When impleminting "Shape" as a "Circle", "circle" should not modificate the inherited methods 

3. L - Liskov Substitution Principle
    - You can always be able to swap a subclass with its superclass. 

4. I - Interface Segregation Principle
    - When inhereting/implementing a class, use a interface to sort the methods such that the client not can acces methods which is not used 

5. D - Dependecy-Inversion Principle
    - Low Level classes must only inherit for abstract super classes. The abstract classes must not "exist" in real life, but the class can have concrete methods. 


## DRY (Don't Repeat Yourself) 
- If your writing the same code twice, break it up into smaller functions or abstract higher for the super classes such that you dont write it twice 

## KISS (Keep it short and Simple)
- Write as short functions as possible and easy to read. If you need to write comments, its possible that you need to write it simpler and shorted. GIVE GOOD NAMES!!!

## YAGNI (You Aint Gonna Need it)
- Use what you have avaliable 


# Patterens:
## Strategy Patteren (functions)
- In your class you define a algorithm that runs independently of the arguments

## Template Patteren (classes)
- Define a template as class and extent it such that functionly is inherited 

## State patteren 
- Projects have different functionality based on if its either finished or still on going 






