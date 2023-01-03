package dev.krysztal.raid.party.impl

import dev.krysztal.raid.foundation.PartyMember
import dev.krysztal.raid.foundation.PartyMemberPermission
import dev.krysztal.raid.party.PartyImplLocal
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class PartyImplLocalTest {
    private lateinit var partyImplLocal: PartyImplLocal

    @BeforeEach
    fun cleanParty() {
        this.partyImplLocal = PartyImplLocal()
    }

    @Test
    fun createParty() {
        val uuid = this.partyImplLocal.createParty()
        assertNotEquals(null, this.partyImplLocal.getParty(uuid))
    }

    @Test
    fun removeParty() {
        val uuid = this.partyImplLocal.createParty()
        this.partyImplLocal.removeParty(uuid)
        assertEquals(0, this.partyImplLocal.getParties().size)
    }

    @Test
    fun clearParty() {
        val uuid = this.partyImplLocal.createParty()
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))

        val captainUUID = UUID.randomUUID()
        this.partyImplLocal.addMember(uuid, PartyMember(captainUUID, PartyMemberPermission.Captain))
        this.partyImplLocal.clearParty(uuid)

        assertEquals(1, this.partyImplLocal.getParty(uuid)?.getMembers()?.size)
        assertEquals(captainUUID, this.partyImplLocal.getParty(uuid)?.getMember(captainUUID)?.uuid)
    }

    @Test
    fun getParty() {
        val uuid = this.partyImplLocal.createParty()
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))

        val captainUUID = UUID.randomUUID()
        this.partyImplLocal.addMember(uuid, PartyMember(captainUUID, PartyMemberPermission.Captain))

        val party = this.partyImplLocal.getParty(uuid)

        assertEquals(5, party?.getMembers()?.size)
    }

    @Test
    fun setParty() {
        val uuid = this.partyImplLocal.createParty()
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))

        val captainUUID = UUID.randomUUID()
        this.partyImplLocal.addMember(uuid, PartyMember(captainUUID, PartyMemberPermission.Captain))

        assertEquals(5, this.partyImplLocal.getParty(uuid)?.getMembers()?.size)

        val party = this.partyImplLocal.getParty(uuid)

        this.partyImplLocal.clearParty(uuid)
        assertEquals(1, this.partyImplLocal.getParty(uuid)?.getMembers()?.size)

        party?.let { this.partyImplLocal.setParty(uuid, it) }
        assertEquals(5, this.partyImplLocal.getParty(uuid)?.getMembers()?.size)

    }

    @Test
    fun getParties() {
        this.partyImplLocal.createParty()
        this.partyImplLocal.createParty()

        assertEquals(2, this.partyImplLocal.getParties().size)
    }

    @Test
    fun setParties() {
        this.getParties()

        this.partyImplLocal.setParties(mutableListOf())

        assertEquals(0, this.partyImplLocal.getParties().size)
    }

    @Test
    fun addMember() {
        val uuid = this.partyImplLocal.createParty()
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))
        this.partyImplLocal.addMember(uuid, PartyMember(UUID.randomUUID()))

        val captainUUID = UUID.randomUUID()
        this.partyImplLocal.addMember(uuid, PartyMember(captainUUID, PartyMemberPermission.Captain))

        assertEquals(5, this.partyImplLocal.getParty(uuid)?.getMembers()?.size)
    }

    @Test
    fun removeMember() {
        val uuid = this.partyImplLocal.createParty()

        val captainUUID = UUID.randomUUID()
        this.partyImplLocal.addMember(uuid, PartyMember(captainUUID, PartyMemberPermission.Captain))

        this.partyImplLocal.removeMember(uuid, captainUUID)

        assertEquals(0, this.partyImplLocal.getParties().size)
    }

    @Test
    fun saveParties() {
    }
}