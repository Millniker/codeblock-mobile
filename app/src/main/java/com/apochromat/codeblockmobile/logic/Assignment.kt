package com.apochromat.codeblockmobile.logic

/**
 *  Блок присвоения.
 *  Позволяет установить существующей переменной значение.
 **/
class Assignment : Block() {
    private var value: Int = 0
    private var name: String = ""
    private var inputValue: String = ""
    private var inputName: String = ""

    init {
        setBlockType("Assignment")
    }

//    private fun initVar(){
//        inputName = inputLeftEdit
//        inputValue = inputRightEdit
//    }

    fun setBlockInput(_name: String, _value: String) {
        inputName = _name
        inputValue = _value
    }

    override fun executeBlock() {
//        initVar()
        val obj = defineInput(heap, inputName)
        if (obj.first == "InputError") {
            setBlockStatus("Undefined object $inputName")
            return
        }
        if (!variableCheck(obj.second)) {
            setBlockStatus("Incorrect naming $inputName")
            return
        }
        when (obj.first) {
            "Array" -> {
                if (!heap.isArrayExist(obj.second)) {
                    setBlockStatus("Undefined array ${obj.second}")
                    return
                }
                val calculated = arithmetics(accessHeap(), inputValue)
                setBlockStatus(calculated.first)
                name = obj.second
                if (calculated.first == "OK") {
                    value = calculated.second
                    heap.setArrayValue(name, obj.third, value)
                }
            }
            "Variable" -> {
                if (!heap.isVariableExist(obj.second)) {
                    setBlockStatus("Undefined variable ${obj.second}")
                    return
                }
                val calculated = arithmetics(accessHeap(), inputValue)
                setBlockStatus(calculated.first)
                name = obj.second
                if (calculated.first == "OK") {
                    value = calculated.second
                    heap.setVariableValue(name, value)
                }
            }
        }
    }

    override fun clearBlockData() {
        value = 0
        name = ""
    }
}