package com.example.abdiwelizo.data

object MockData {
    val users = listOf(
        User(
            id = "U001",
            name = "Amina Omar",
            email = "student@umma.ac.ke",
            role = UserRole.STUDENT,
            regOrStaffNumber = "BIT/2023/4589",
            department = "Department of Computing & IT",
            programOrDesignation = "Bachelor of Science in Information Technology",
            phone = "+254 712 345 678"
        ),
        User(
            id = "U002",
            name = "Dr. Hassan Al-Mumin",
            email = "lecturer@umma.ac.ke",
            role = UserRole.LECTURER,
            regOrStaffNumber = "LEC/2018/1102",
            department = "Department of Computing & IT",
            programOrDesignation = "Senior Lecturer & Researcher",
            phone = "+254 722 987 654"
        ),
        User(
            id = "U003",
            name = "Prof. Fatima Juma",
            email = "admin@umma.ac.ke",
            role = UserRole.ADMIN,
            regOrStaffNumber = "ADM/2015/0012",
            department = "University Administration",
            programOrDesignation = "Academic Registrar",
            phone = "+254 733 111 222"
        ),
        User(
            id = "U004",
            name = "Yusuf Kassim",
            email = "support@umma.ac.ke",
            role = UserRole.SUPPORT,
            regOrStaffNumber = "SUP/2020/3344",
            department = "ICT & Student Welfare",
            programOrDesignation = "Support Help Desk Officer",
            phone = "+254 744 555 666"
        )
    )

    val demoCredentials = mapOf(
        "student@umma.ac.ke" to "Student123",
        "lecturer@umma.ac.ke" to "Lecturer123",
        "admin@umma.ac.ke" to "Admin123",
        "support@umma.ac.ke" to "Support123"
    )

    val academicUnits = mutableListOf(
        AcademicUnit(
            id = "ACT101",
            unitCode = "BIT 311",
            unitName = "Mobile Application Development",
            credits = 3,
            semester = "Semester II, 2024/2025",
            description = "Advanced Android development using Kotlin, Jetpack Compose, Material 3, and offline architectures.",
            lecturerName = "Dr. Hassan Al-Mumin",
            isRegistered = true
        ),
        AcademicUnit(
            id = "ACT102",
            unitCode = "BIT 312",
            unitName = "Enterprise Software Architecture",
            credits = 3,
            semester = "Semester II, 2024/2025",
            description = "Design patterns, microservices, enterprise integration patterns, and cloud deployment.",
            lecturerName = "Dr. Hassan Al-Mumin",
            isRegistered = true
        ),
        AcademicUnit(
            id = "ACT103",
            unitCode = "BIT 313",
            unitName = "Information Security & Cryptography",
            credits = 3,
            semester = "Semester II, 2024/2025",
            description = "Principles of cybersecurity, encryption algorithms, network security, and risk assessment.",
            lecturerName = "Prof. Fatima Juma",
            isRegistered = true
        ),
        AcademicUnit(
            id = "ACT104",
            unitCode = "BIT 314",
            unitName = "Database Systems Implementation",
            credits = 4,
            semester = "Semester II, 2024/2025",
            description = "Query optimization, transaction management, distributed databases, and NoSQL systems.",
            lecturerName = "Dr. Hassan Al-Mumin",
            isRegistered = false
        ),
        AcademicUnit(
            id = "ACT105",
            unitCode = "BIT 315",
            unitName = "Artificial Intelligence & Machine Learning",
            credits = 4,
            semester = "Semester II, 2024/2025",
            description = "Neural networks, supervised and unsupervised learning, natural language processing.",
            lecturerName = "Dr. Hassan Al-Mumin",
            isRegistered = false
        )
    )

    val transcripts = listOf(
        SemesterTranscript(
            semesterName = "Semester I, 2024/2025",
            academicYear = "2024/2025",
            gpa = 3.85,
            units = listOf(
                UnitResult("BIT 301", "Advanced Data Structures", 78, "A", "Semester I, 2024/2025"),
                UnitResult("BIT 302", "Web Systems & Technologies", 82, "A", "Semester I, 2024/2025"),
                UnitResult("BIT 303", "Operating Systems", 74, "B+", "Semester I, 2024/2025"),
                UnitResult("BIT 304", "Software Engineering Principles", 80, "A", "Semester I, 2024/2025")
            )
        ),
        SemesterTranscript(
            semesterName = "Semester II, 2023/2024",
            academicYear = "2023/2024",
            gpa = 3.72,
            units = listOf(
                UnitResult("BIT 211", "Object Oriented Programming II", 76, "B+", "Semester II, 2023/2024"),
                UnitResult("BIT 212", "Computer Networks & Security", 70, "B", "Semester II, 2023/2024"),
                UnitResult("BIT 213", "Database Management Systems", 85, "A", "Semester II, 2023/2024")
            )
        )
    )

    val timetables = mutableListOf(
        TimetableItem("T1", "Monday", "08:00 AM - 10:00 AM", "BIT 311", "Mobile Application Development", "Lab 3, ICT Complex", "Dr. Hassan Al-Mumin"),
        TimetableItem("T2", "Monday", "10:30 AM - 12:30 PM", "BIT 312", "Enterprise Software Architecture", "Lecture Hall 2", "Dr. Hassan Al-Mumin"),
        TimetableItem("T3", "Tuesday", "02:00 PM - 05:00 PM", "BIT 313", "Information Security & Cryptography", "Room 104", "Prof. Fatima Juma"),
        TimetableItem("T4", "Thursday", "09:00 AM - 12:00 PM", "BIT 311", "Mobile Application Development (Practical)", "Lab 1", "Dr. Hassan Al-Mumin"),
        TimetableItem("T5", "Friday", "10:00 AM - 01:00 PM", "BIT 312", "Enterprise Software Architecture", "Auditorium A", "Dr. Hassan Al-Mumin"),
        // Exams
        TimetableItem("E1", "Wednesday", "09:00 AM - 12:00 PM", "BIT 311", "Mobile Application Development Exam", "Main Exam Hall", "Dr. Hassan Al-Mumin", isExam = true, examDate = "2025-07-15", examRules = "Bring student ID, no mobile phones allowed, arrive 30 mins prior."),
        TimetableItem("E2", "Thursday", "02:00 PM - 05:00 PM", "BIT 313", "Information Security Exam", "Main Exam Hall", "Prof. Fatima Juma", isExam = true, examDate = "2025-07-18", examRules = "Open book exam, calculator permitted.")
    )

    val assignments = mutableListOf(
        AssignmentItem("A1", "BIT 311", "Mobile Application Development", "Jetpack Compose UI Task", "Develop a fully responsive dashboard using Jetpack Compose and Material 3 with emerald theme.", "2025-05-30 23:59", 20, "Pending"),
        AssignmentItem("A2", "BIT 312", "Enterprise Software Architecture", "Microservices Design Document", "Submit a comprehensive architectural diagram and documentation for an e-commerce platform.", "2025-06-05 18:00", 30, "Submitted", "selected_architecture_spec.pdf", "2025-05-10 14:20"),
        AssignmentItem("A3", "BIT 313", "Information Security & Cryptography", "RSA Encryption Implementation", "Implement a functional RSA key generation and encryption script in Kotlin or Python.", "2025-06-12 23:59", 25, "Graded", "rsa_implementation.kt", "2025-05-01 10:15", 23, "Excellent work on modular exponentiation!")
    )

    val libraryBooks = mutableListOf(
        LibraryBook("B1", "Kotlin in Action", "Dmitry Jemerov", "Programming", "Comprehensive guide to Kotlin language features, coroutines, and Android development.", 5, 3, "Shelf A-12"),
        LibraryBook("B2", "Android Programming: The Big Nerd Ranch Guide", "Bill Phillips", "Mobile Development", "Best-selling guide to building robust Android applications with modern Jetpack libraries.", 4, 1, "Shelf B-04"),
        LibraryBook("B3", "Enterprise Integration Patterns", "Gregor Hohpe", "Software Architecture", "Designing, building, and deploying messaging solutions across enterprise systems.", 3, 2, "Shelf C-08"),
        LibraryBook("B4", "Applied Cryptography", "Bruce Schneier", "Security", "Protocols, algorithms, and source code in C for secure communications.", 2, 0, "Shelf D-01", isReserved = true, dueDate = "2025-05-25")
    )

    val feeRecord = FeeRecord(
        totalCharged = 145000.0,
        totalPaid = 110000.0,
        scholarshipInfo = "Constituency Bursary Allocation: KES 15,000 (Approved)",
        statements = listOf(
            FeeStatement("Sem II 2024/2025", "Tuition Fee", 90000.0, 70000.0, "2025-01-10"),
            FeeStatement("Sem II 2024/2025", "Library & Computer Lab Fee", 25000.0, 25000.0, "2025-01-10"),
            FeeStatement("Sem I 2024/2025", "Tuition & Activity Fee", 30000.0, 15000.0, "2024-09-05")
        ),
        transactions = listOf(
            FeeTransaction("MPE-9883421", "2025-02-15", 50000.0, "M-Pesa", "Completed"),
            FeeTransaction("MPE-8712390", "2025-01-12", 45000.0, "M-Pesa", "Completed"),
            FeeTransaction("BANK-44321", "2024-09-10", 15000.0, "Equity Bank", "Completed")
        )
    )

    val announcements = mutableListOf(
        Announcement("AN1", "Semester II 2024/2025 Examinations Timetable Released", "Exam", "All students are advised to download and review their examination timetables. Report any clashes to the Registrar Academic before 10th June 2025.", "2025-05-18", "Academic Registrar", false, false),
        Announcement("AN2", "Annual Umma University Innovation & Tech Hackathon 2025", "General", "Register your team for the upcoming hackathon showcasing mobile and AI solutions for community development. Cash prizes await!", "2025-05-15", "Dean of Students", false, false),
        Announcement("AN3", "URGENT: Library Book Return Notice", "Library", "All overdue books must be returned by Friday this week to avoid accumulation of daily fines.", "2025-05-12", "University Librarian", true, true),
        Announcement("AN4", "Fee Payment Deadline Reminder", "Financial", "Second installment of tuition fees is due on or before 30th May 2025. Ensure prompt clearance.", "2025-05-10", "Finance Department", false, false)
    )

    val supportTickets = mutableListOf(
        SupportTicket("TC1", "TKT-2025-091", "ICT Support", "Wi-Fi Access Issue in Hostels", "Unable to connect student Wi-Fi network using credentials from Block B.", "In Progress", "2025-05-16", "Amina Omar", mutableListOf(
            TicketResponse("Yusuf Kassim", "We have checked your account radius profile. Please re-enter your password on the portal.", "2025-05-16 14:30", true)
        )),
        SupportTicket("TC2", "TKT-2025-084", "Finance", "M-Pesa Receipt Reflection Delay", "Payment made on 15th Feb yet to reflect on student portal statement.", "Resolved", "2025-02-16", "Amina Omar", mutableListOf(
            TicketResponse("Finance Desk", "Payment has been successfully reconciled and posted to your ledger.", "2025-02-16 11:00", true)
        ))
    )

    val campusFacilities = listOf(
        CampusFacility("Main Administration Block", "University executive offices, admissions, finance, and academic registrar.", "08:00 AM - 05:00 PM", "+254 45 24200", "Main Gate Entrance"),
        CampusFacility("ICT Complex & Computer Labs", "State-of-the-art computer labs, networking hubs, and student support helpdesk.", "07:30 AM - 09:00 PM", "+254 45 24205", "Block C"),
        CampusFacility("Umma University Library", "Vast collection of physical and digital academic resources, quiet study zones.", "08:00 AM - 09:30 PM", "+254 45 24210", "Library Tower"),
        CampusFacility("University Cafeteria", "Halal hot meals, beverages, snacks, and spacious dining area.", "07:00 AM - 08:00 PM", "+254 45 24215", "Student Center"),
        CampusFacility("Student Hostels", "Secure male and female residential halls with 24/7 security.", "Open 24 Hours", "+254 45 24220", "Eastern Campus Wing")
    )

    val learningMaterials = mutableListOf(
        LearningMaterial("LM1", "BIT 311", "Lecture 1: Introduction to Jetpack Compose & UI Architecture", "PDF", "https://umma.ac.ke/materials/bit311_l1.pdf", "2025-04-10"),
        LearningMaterial("LM2", "BIT 311", "Lecture 2: State Management and ViewModel Patterns", "PDF", "https://umma.ac.ke/materials/bit311_l2.pdf", "2025-04-17"),
        LearningMaterial("LM3", "BIT 311", "Android Architecture & Navigation Video Walkthrough", "Video", "https://umma.ac.ke/materials/bit311_vid1.mp4", "2025-04-24"),
        LearningMaterial("LM4", "BIT 312", "Microservices and RESTful API Design Slides", "Slides", "https://umma.ac.ke/materials/bit312_slides.pdf", "2025-04-12")
    )
}
