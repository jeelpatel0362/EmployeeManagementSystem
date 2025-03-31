abstract class Person(open val id: Int, open val name: String) {
    abstract fun getDetails(): String
}

data class Project(val projectName: String)

interface Assignable {
    fun assign(project: Project)
}

data class Employee(
    override val id: Int,
    override val name: String,
    val age: Int,
    val department: String
) : Person(id, name), Assignable {

    private val projects = mutableListOf<Project>()

    override fun assign(project: Project) {
        projects.add(project)
    }

    override fun getDetails(): String {
        val projectNames = if (projects.isEmpty()) "None" else projects.joinToString { it.projectName }
        return "ID: $id, Name: $name, Age: $age, Department: $department, Projects: $projectNames"
    }

    fun showProjects() {
        if (projects.isEmpty()) {
            println("$name has no assigned projects.")
        } else {
            println("Projects assigned to $name:")
            for (project in projects) {
                println("- ${project.projectName}")
            }
        }
    }
}

class Company {
    private var employees = mutableListOf<Employee>()
    private var departments = mutableListOf<String>()
    private var departmentEmployee = mutableMapOf<String, MutableList<Employee>>()

    fun addEmployee(id: Int, name: String, age: Int, department: String) {
        if (employees.any { it.id == id }) {
            println("Employee with ID $id already exists.")
            return
        }
        if (department !in departments) {
            println("Department '$department' does not exist. Please add it first.")
            return
        }
        val employee = Employee(id, name, age, department)
        employees.add(employee)
        departmentEmployee[department]?.add(employee)
        println("Employee $name added to $department successfully.")
    }

    fun addDepartment(department: String) {
        if (department in departments) {
            println("Department '$department' already exists.")
            return
        }
        departments.add(department)
        departmentEmployee[department] = mutableListOf()
        println("Department '$department' added successfully.")
    }

    fun assignProjectToEmployee(employeeId: Int, projectName: String) {
        for (employee in employees) {
            if (employee.id == employeeId) {
                val project = Project(projectName)
                employee.assign(project)
                println("Project '$projectName' assigned to ${employee.name}.")
                return
            }
        }
        println("Employee not found.")
    }

    fun displayDetails(employeeId: Int) {
        for (employee in employees) {
            if (employee.id == employeeId) {
                println("\nEmployee Details:")
                println("ID: ${employee.id}")
                println("Name: ${employee.name}")
                println("Age: ${employee.age}")
                println("Department: ${employee.department}")
                employee.showProjects()
                return
            }
        }
        println("Employee not found.")
    }

    fun listDepartments() {
        if (departments.isEmpty()) {
            println("No departments available.")
            return
        }

        println("\nDepartments and Employees:")
        for (department in departments) {
            println("- $department:")
            for (employee in departmentEmployee[department]!!) {
                println("  - ${employee.name}")
            }
        }
    }
}

fun main() {
    val company = Company()

    while (true) {
        println("\nChoose an option:")
        println("1. Add Department")
        println("2. Add Employee")
        println("3. Assign Project to Employee")
        println("4. List Departments and Employees")
        println("5. Show Employee Details")
        println("6. Exit")

        val choice = readln().toInt()
        when (choice) {
            1 -> {
                print("Enter Department Name: ")
                val dept = readln()
                company.addDepartment(dept)
            }
            2 -> {
                print("Enter Employee ID: ")
                val id = readln().toInt()
                print("Enter Employee Name: ")
                val name = readln()
                print("Enter Employee Age: ")
                val age = readln().toInt()
                print("Enter Employee Department: ")
                val department = readln()
                company.addEmployee(id, name, age, department)
            }
            3 -> {
                print("Enter Employee ID: ")
                val empId = readln().toInt()
                print("Enter Project Name: ")
                val projectName = readln()
                company.assignProjectToEmployee(empId, projectName)
            }
            4 -> company.listDepartments()
            5 -> {
                print("Enter Employee ID: ")
                val empId = readln().toInt()
                company.displayDetails(empId)
            }
            6 -> {
                println("Exiting program.")
                return
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}
