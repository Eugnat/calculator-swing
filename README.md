# calculator-swing
This is a Swing-based simple version of the calculator. The calculator recognizes parentheses and applies a recursion function in this case to calculate the final result.
The following patterns can be calculated. For the sake of simplicity no spaces are allowed between signs and digits (otherwise a parsing error occurs):
4+5
4+5+3
4+(3*5)
4-(-5)
5-((4-3)+(45-3)+(4/5))

The list is not exhaustive.

If an error is recognized (wrong notation or parsing error), ERROR is displayed in the output field.

A Swing GridBagLayout was used for construction of the layout.

