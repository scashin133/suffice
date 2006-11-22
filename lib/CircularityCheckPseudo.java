
VarMap varmap = map of cell names to their values for the parser.

Object getValue(Stack prevVisitedCells) throws CircularityException{
	if currCell is in prevVisitedCells {
		throw CircularityException
	}	else{
		Object[] getCellsBeingRefferedTo

		for(Cell c : cellsRefferingTo){
			push currCell
			value = c.getValue(Stack)
			varmap.associate(c, value)
			pop

		}

		value = eval(varmap, null)
		return value
	}

}