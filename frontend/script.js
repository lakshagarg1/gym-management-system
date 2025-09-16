document.addEventListener('DOMContentLoaded', () => {
    const sections = document.querySelectorAll('.entity-section');
    const navButtons = document.querySelectorAll('.nav-btn');

    navButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const target = btn.getAttribute('data-target');
            sections.forEach(section => {
                section.style.display = section.id === target ? 'block' : 'none';
            });
            // Load data for the selected section
            if (target === 'member-section') loadMembers();
            else if (target === 'equipment-section') loadEquipment();
            else if (target === 'healthmetric-section') loadHealthMetrics();
            else if (target === 'membershipplan-section') loadMembershipPlans();
            else if (target === 'payment-section') loadPayments();
            else if (target === 'schedule-section') loadSchedules();
            else if (target === 'trainer-section') loadTrainers();
        });
    });

    // Trigger click on first nav button to load initial section
    navButtons[0].click();

    // Member form submit
    document.getElementById('memberForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            name: document.getElementById('member-name').value,
            email: document.getElementById('member-email').value,
            address: document.getElementById('member-address').value,
            phone: document.getElementById('member-phone').value,
            membershipPlanId: parseInt(document.getElementById('member-membershipPlanId').value) || null
        };
        await postData('/members', data, 'member-result');
        loadMembers();
        e.target.reset();
    });

    // Equipment form submit
    document.getElementById('equipmentForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            name: document.getElementById('equipment-name').value,
            type: document.getElementById('equipment-type').value,
            purchaseDate: document.getElementById('equipment-purchaseDate').value,
            status: document.getElementById('equipment-status').value
        };
        await postData('/equipment', data, 'equipment-result');
        loadEquipment();
        e.target.reset();
    });

    // HealthMetric form submit
    document.getElementById('healthMetricForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            memberId: parseInt(document.getElementById('healthmetric-memberId').value),
            date: document.getElementById('healthmetric-date').value,
            weight: parseFloat(document.getElementById('healthmetric-weight').value),
            height: parseFloat(document.getElementById('healthmetric-height').value),
            bmi: parseFloat(document.getElementById('healthmetric-bmi').value) || null,
            notes: document.getElementById('healthmetric-notes').value
        };
        await postData('/healthmetrics', data, 'healthmetric-result');
        loadHealthMetrics();
        e.target.reset();
    });

    // MembershipPlan form submit
    document.getElementById('membershipPlanForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            name: document.getElementById('membershipplan-name').value,
            description: document.getElementById('membershipplan-description').value,
            price: parseFloat(document.getElementById('membershipplan-price').value),
            durationMonths: parseInt(document.getElementById('membershipplan-durationMonths').value),
            benefits: document.getElementById('membershipplan-benefits').value
        };
        await postData('/membershipplans', data, 'membershipplan-result');
        loadMembershipPlans();
        e.target.reset();
    });

    // Payment form submit
    document.getElementById('paymentForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            memberId: parseInt(document.getElementById('payment-memberId').value),
            amount: parseFloat(document.getElementById('payment-amount').value),
            status: document.getElementById('payment-status').value
        };
        await postData('/payments', data, 'payment-result');
        loadPayments();
        e.target.reset();
    });

    // Schedule form submit
    document.getElementById('scheduleForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            trainerId: parseInt(document.getElementById('schedule-trainerId').value),
            startTime: document.getElementById('schedule-startTime').value,
            endTime: document.getElementById('schedule-endTime').value
        };
        await postData('/schedules', data, 'schedule-result');
        loadSchedules();
        e.target.reset();
    });

    // Trainer form submit
    document.getElementById('trainerForm').addEventListener('submit', async e => {
        e.preventDefault();
        const data = {
            name: document.getElementById('trainer-name').value,
            specialization: document.getElementById('trainer-specialization').value
        };
        await postData('/trainers', data, 'trainer-result');
        loadTrainers();
        e.target.reset();
    });

    // Helper function to POST data
    async function postData(endpoint, data, resultId) {
        const resultDiv = document.getElementById(resultId);
        resultDiv.style.display = 'none';
        resultDiv.className = '';
        try {
            const response = await fetch('http://localhost:8082' + endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });
            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Failed to add');
            }
            const resData = await response.json();
            resultDiv.textContent = 'Added successfully! ID: ' + resData.id;
            resultDiv.className = 'success';
            resultDiv.style.display = 'block';
        } catch (error) {
            resultDiv.textContent = 'Error: ' + error.message;
            resultDiv.className = 'error';
            resultDiv.style.display = 'block';
        }
    }

    // Load functions for each entity
    async function loadMembers() {
        const list = document.getElementById('member-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/members');
            const data = await res.json();
            data.forEach(m => {
                const li = document.createElement('li');
                li.textContent = `ID: ${m.id}, Name: ${m.name}, Email: ${m.email}, Phone: ${m.phone}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load members';
        }
    }

    async function loadEquipment() {
        const list = document.getElementById('equipment-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/equipment');
            const data = await res.json();
            data.forEach(e => {
                const li = document.createElement('li');
                li.textContent = `ID: ${e.id}, Name: ${e.name}, Type: ${e.type}, Purchase Date: ${e.purchaseDate}, Status: ${e.status}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load equipment';
        }
    }

    async function loadHealthMetrics() {
        const list = document.getElementById('healthmetric-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/healthmetrics');
            const data = await res.json();
            data.forEach(h => {
                const li = document.createElement('li');
                li.textContent = `ID: ${h.id}, Member ID: ${h.memberId}, Date: ${h.date}, Weight: ${h.weight}, Height: ${h.height}, BMI: ${h.bmi}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load health metrics';
        }
    }

    async function loadMembershipPlans() {
        const list = document.getElementById('membershipplan-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/membershipplans');
            const data = await res.json();
            data.forEach(mp => {
                const li = document.createElement('li');
                li.textContent = `ID: ${mp.id}, Name: ${mp.name}, Price: ${mp.price}, Duration: ${mp.durationMonths} months`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load membership plans';
        }
    }

    async function loadPayments() {
        const list = document.getElementById('payment-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/payments');
            const data = await res.json();
            data.forEach(p => {
                const li = document.createElement('li');
                li.textContent = `ID: ${p.id}, Member ID: ${p.memberId}, Amount: ${p.amount}, Status: ${p.status}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load payments';
        }
    }

    async function loadSchedules() {
        const list = document.getElementById('schedule-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/schedules');
            const data = await res.json();
            data.forEach(s => {
                const li = document.createElement('li');
                li.textContent = `ID: ${s.id}, Trainer ID: ${s.trainerId}, Start: ${s.startTime}, End: ${s.endTime}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load schedules';
        }
    }

    async function loadTrainers() {
        const list = document.getElementById('trainer-list');
        list.innerHTML = '';
        try {
            const res = await fetch('http://localhost:8082/trainers');
            const data = await res.json();
            data.forEach(t => {
                const li = document.createElement('li');
                li.textContent = `ID: ${t.id}, Name: ${t.name}, Specialization: ${t.specialization}`;
                list.appendChild(li);
            });
        } catch (e) {
            list.textContent = 'Failed to load trainers';
        }
    }
});
