data class Employee(var id: Int, var name: String, var age: Int, var department: String, var project: String)

class Company {

    private var employees = mutableListOf<String>()
    private var departments = mutableListOf<String>()

    private var departmentEmployee = mutableMapOf<String, MutableList<String>>()

    fun addEmployee(employee: String) {

        if (!employees.contains(employee)) {
            employees.add(employee)
            println("$employee Added successfully...")
        } else {
            println("$employee already Exists.")
        }
    }

    fun addDepartment(department: String) {

        if (!departments.contains(department)) {
            departments.add(department)
            departmentEmployee[department] = mutableListOf()
            println("$department Added successfully...")
        } else {
            println("$departments already Exists.")
        }
    }

    fun assignDepartmentEmployee(employee: String,department: String) {
        if (!employees.contains(employee)) {
            println("Error : Employee $employee does not exist...")
            return
        }
        if (!departments.contains(department)) {
            println("Error : Employee $department does not exist...")
            return
        }

        val employeeList = departmentEmployee[department]!!

        if (!employeeList.contains(employee)) {
            employeeList.add(employee)
            println("Assigned $employee to $department")
        } else {
            println("$employee in $department is already Exists.")
        }
    }

        fun displayDetails(){

            println("Company Details :")
            println("Employee : $employees")
            println("Department : $departments")

        }

    }





fun main() {

    var company = Company()

    company.addEmployee("Jeel")
    company.addDepartment("gfgvh")

    company.assignDepartmentEmployee("Jeel","gfgvh")

    company.displayDetails()

}