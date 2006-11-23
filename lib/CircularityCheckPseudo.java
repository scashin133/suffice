
Cell[] cellsReferingToThisCell
Cell[] cellsReferencedInExpression

// recompiles given cell and returns its compiled & evaluated value
String getValue(Stack prevVisitedCells) throws CircularityException{
	if currCell is in prevVisitedCells {
		throw CircularityException
	}	
	else{

		VarMap varmap
		
		for(Cell c : cellsReferencedInExpression){
			push c
			value = c.getValue(Stack)
			varmap.associate(c, value)
			pop

		}

		value = eval(varmap, null)
		return value
		
	}

}