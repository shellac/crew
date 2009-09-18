/**
 * Copyright (c) 2008-2009, University of Bristol
 * Copyright (c) 2008-2009, University of Manchester
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1) Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3) Neither the names of the University of Bristol and the
 *    University of Manchester nor the names of their
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package net.crew_vre.events.service.impl;

import net.crew_vre.events.dao.EventDao;
import net.crew_vre.events.dao.LocationDao;
import net.crew_vre.events.dao.RoleDao;
import net.crew_vre.events.domain.Event;
import net.crew_vre.events.service.EventService;
import net.crew_vre.events.service.PaperService;

/**
 * @author Mike Jones (mike.a.jones@bristol.ac.uk)
 * @version $Id: EventServiceImpl.java 1188 2009-03-31 13:09:20Z cmmaj $
 */
public class EventServiceImpl implements EventService {

    public EventServiceImpl(final EventDao eventDao, final LocationDao locationDao,
                            final RoleDao roleDao, final PaperService paperService) {
        this.eventDao = eventDao;
        this.roleDao = roleDao;
        this.locationDao = locationDao;
        this.paperService = paperService;
    }

    public Event getEventById(final String id) {

        Event event = eventDao.findEventById(id);
        event.setLocations(locationDao.findLocationByEvent(id));
        event.setRoles(roleDao.findRolesByEvent(id));
        event.setPapers(paperService.findPapersRelatedToEvent(id));
        return event;
    }

    /**
     * DAO to access the events.
     */
    private EventDao eventDao;

    /**
     * DAO to access an event locations
     */
    private LocationDao locationDao;

    /**
     * DAO to access roles
     */
    private RoleDao roleDao;

    /**
     * Details about papers
     */
    private PaperService paperService;
}