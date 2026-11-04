package com.example.abdiwelizo.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AppRepository {
    private val _users = MutableStateFlow(MockData.users)
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _academicUnits = MutableStateFlow(MockData.academicUnits.toList())
    val academicUnits: StateFlow<List<AcademicUnit>> = _academicUnits.asStateFlow()

    private val _timetables = MutableStateFlow(MockData.timetables.toList())
    val timetables: StateFlow<List<TimetableItem>> = _timetables.asStateFlow()

    private val _assignments = MutableStateFlow(MockData.assignments.toList())
    val assignments: StateFlow<List<AssignmentItem>> = _assignments.asStateFlow()

    private val _libraryBooks = MutableStateFlow(MockData.libraryBooks.toList())
    val libraryBooks: StateFlow<List<LibraryBook>> = _libraryBooks.asStateFlow()

    private val _feeRecord = MutableStateFlow(MockData.feeRecord)
    val feeRecord: StateFlow<FeeRecord> = _feeRecord.asStateFlow()

    private val _announcements = MutableStateFlow(MockData.announcements.toList())
    val announcements: StateFlow<List<Announcement>> = _announcements.asStateFlow()

    private val _supportTickets = MutableStateFlow(MockData.supportTickets.toList())
    val supportTickets: StateFlow<List<SupportTicket>> = _supportTickets.asStateFlow()

    private val _learningMaterials = MutableStateFlow(MockData.learningMaterials.toList())
    val learningMaterials: StateFlow<List<LearningMaterial>> = _learningMaterials.asStateFlow()

    // Actions
    fun registerUnit(unitId: String) {
        _academicUnits.value = _academicUnits.value.map {
            if (it.id == unitId) it.copy(isRegistered = true) else it
        }
    }

    fun dropUnit(unitId: String) {
        _academicUnits.value = _academicUnits.value.map {
            if (it.id == unitId) it.copy(isRegistered = false) else it
        }
    }

    fun submitAssignment(assignmentId: String, fileName: String) {
        _assignments.value = _assignments.value.map {
            if (it.id == assignmentId) {
                it.copy(
                    status = "Submitted",
                    submittedFileUri = fileName,
                    submittedAt = "Just now"
                )
            } else it
        }
    }

    fun gradeAssignment(assignmentId: String, score: Int, feedback: String) {
        _assignments.value = _assignments.value.map {
            if (it.id == assignmentId) {
                it.copy(
                    status = "Graded",
                    gradeScore = score,
                    lecturerFeedback = feedback
                )
            } else it
        }
    }

    fun reserveBook(bookId: String) {
        _libraryBooks.value = _libraryBooks.value.map {
            if (it.id == bookId && it.availableCopies > 0) {
                it.copy(
                    isReserved = true,
                    availableCopies = it.availableCopies - 1,
                    dueDate = "2025-06-15"
                )
            } else it
        }
    }

    fun addAnnouncement(announcement: Announcement) {
        _announcements.value = listOf(announcement) + _announcements.value
    }

    fun createSupportTicket(ticket: SupportTicket) {
        _supportTickets.value = listOf(ticket) + _supportTickets.value
    }

    fun addTicketResponse(ticketId: String, response: TicketResponse) {
        _supportTickets.value = _supportTickets.value.map {
            if (it.id == ticketId) {
                it.responses.add(response)
                it
            } else it
        }
    }

    fun updateTicketStatus(ticketId: String, newStatus: String) {
        _supportTickets.value = _supportTickets.value.map {
            if (it.id == ticketId) {
                it.copy(status = newStatus)
            } else it
        }
    }

    fun addUser(user: User) {
        _users.value = _users.value + user
    }

    fun updateUser(updatedUser: User) {
        _users.value = _users.value.map { if (it.id == updatedUser.id) updatedUser else it }
    }

    fun deleteUser(userId: String) {
        _users.value = _users.value.filter { it.id != userId }
    }
}
