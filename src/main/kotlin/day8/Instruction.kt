package day8

data class Instruction(var type: InstructionType, val value: Int) {


    override fun toString(): String {
        return "$type $value"
    }
}
