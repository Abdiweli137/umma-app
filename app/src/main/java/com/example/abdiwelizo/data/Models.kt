package com.example.abdiwelizo.data

enum class UserRole {
    STUDENT, LECTURER, ADMIN, SUPPORT
}

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val regOrStaffNumber: String,
    val department: String,
    val programOrDesignation: String,
    val phone: String,
    val avatarUrl: String = ""
)

data class AcademicUnit(
    val id: String,
    val unitCode: String,
    val unitName: String,
    val credits: Int,
    val semester: String,
    val description: String,
    val lecturerName: String,
    var isRegistered: Boolean = false
)

data class UnitResult(
    val unitCode: String,
    val unitName: String,
    val marks: Int,
    val grade: String,
    val semester: String
)

data class SemesterTranscript(
    val semesterName: String,
    val academicYear: String,
    val units: List<UnitResult>,
    val gpa: Double
)

data class TimetableItem(
    val id: String,
    val dayOfWeek: String, // e.g., "Monday"
    val timeString: String, // e.g., "08:00 AM - 10:00 AM"
    val unitCode: String,
    val unitName: String,
    val venue: String,
    val lecturerName: String,
    val isExam: Boolean = false,
    val examDate: String = "",
    val examRules: String = ""
)

data class AssignmentItem(
    val id: String,
    val unitCode: String,
    val unitName: String,
    val title: String,
    val description: String,
    val deadline: String,
    val totalMarks: Int,
    val status: String = "Pending", // Pending, Submitted, Graded
    val submittedFileUri: String = "",
    val submittedAt: String = "",
    val gradeScore: Int? = null,
    val lecturerFeedback: String = ""
)

data class LibraryBook(
    val id: String,
    val title: String,
    val author: String,
    val category: String,
    val description: String,
    val totalCopies: Int,
    val availableCopies: Int,
    val location: String,
    var isReserved: Boolean = false,
    var dueDate: String = ""
)

data class FeeStatement(
    val term: String,
    val description: String,
    val amountCharged: Double,
    val amountPaid: Double,
    val date: String
)

data class FeeTransaction(
    val referenceNo: String,
    val date: String,
    val amount: Double,
    val mode: String,
    val status: String
)

data class FeeRecord(
    val totalCharged: Double,
    val totalPaid: Double,
    val statements: List<FeeStatement>,
    val transactions: List<FeeTransaction>,
    val scholarshipInfo: String
) {
    val balance: Double get() = totalCharged - totalPaid
}

data class Announcement(
    val id: String,
    val title: String,
    val category: String, // Academic, General, Exam, Financial, Emergency
    val content: String,
    val date: String,
    val author: String,
    var isRead: Boolean = false,
    val isEmergency: Boolean = false
)

data class TicketResponse(
    val senderName: String,
    val message: String,
    val timestamp: String,
    val isStaff: Boolean
)

data class SupportTicket(
    val id: String,
    val ticketNo: String,
    val category: String,
    val subject: String,
    val description: String,
    var status: String, // Submitted, Under Review, In Progress, Resolved, Rejected
    val date: String,
    val studentName: String,
    val responses: MutableList<TicketResponse> = mutableListOf()
)

data class CampusFacility(
    val name: String,
    val description: String,
    val openingHours: String,
    val contact: String,
    val location: String
)

data class CafeteriaItem(
    val name: String,
    val price: Double,
    val category: String,
    val description: String
)

data class TransportSchedule(
    val route: String,
    val departureTime: String,
    val busNo: String,
    val fare: Double
)

data class LearningMaterial(
    val id: String,
    val unitCode: String,
    val title: String,
    val type: String, // PDF, Video, Slides
    val url: String,
    val date: String
)
