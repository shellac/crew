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
package net.crew_vre.events.dao.impl;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import net.crew_vre.events.dao.PaperDao;
import net.crew_vre.events.domain.Paper;
import net.crew_vre.jena.vocabulary.IUGO;

import java.util.ArrayList;
import java.util.List;

import org.caboto.jena.db.Database;
import org.caboto.jena.db.Results;
import org.caboto.jena.db.Utils;

/**
 * @author Mike Jones (mike.a.jones@bristol.ac.uk)
 * @version $Id: PaperDaoImpl.java 1188 2009-03-31 13:09:20Z cmmaj $
 */
public class PaperDaoImpl implements PaperDao {

    public PaperDaoImpl(final Database database) {
        this.database = database;

        this.sparqlPaperTitle = Utils.loadSparql("/sparql/paper-details.rq");
        this.sparqlRelatedToEvents =
                Utils.loadSparql("/sparql/paper-details-related-to-event.rq");
    }

    public Paper findPaperById(final String id) {

        Paper paper = null;

        // create the bindings
        QuerySolutionMap initialBindings = new QuerySolutionMap();
        initialBindings.add("id", ModelFactory.createDefaultModel().createResource(id));

        Results res = database.executeSelectQuery(sparqlPaperTitle,
                initialBindings);
        ResultSet rs = res.getResults();

        // there should only be one...
        while (rs.hasNext()) {
            paper = paperDetails(rs.nextSolution());
        }

        res.close();

        return paper;
    }

    public List<Paper> findPapersRelatedToEvent(String eventId) {

        List<Paper> results = new ArrayList<Paper>();

        // create the bindings
        QuerySolutionMap initialBindings = new QuerySolutionMap();
        initialBindings.add("eventId", ModelFactory.createDefaultModel().createResource(eventId));

        Results res = database.executeSelectQuery(sparqlRelatedToEvents,
                initialBindings);
        ResultSet rs = res.getResults();

        while (rs.hasNext()) {
            results.add(paperDetails(rs.nextSolution()));
        }

        res.close();

        return results;
    }


    private Paper paperDetails(QuerySolution qs) {

        Paper paper = new Paper();

        Resource resource = qs.getResource("id");
        paper.setId(resource.getURI());

        if (qs.getResource("graph") != null) {
            paper.setGraph(qs.getResource("graph").getURI());
        }

        // get the title
        if (qs.getLiteral("title") != null) {
            paper.setTitle(qs.getLiteral("title").getLexicalForm());
        }

        // get the description
        if (qs.getLiteral("description") != null) {
            paper.setDescription(qs.getLiteral("description").getLexicalForm());
        }

        // get the type
        if (qs.getResource("type") != null) {
            if (qs.getResource("type").equals(IUGO.Retrievable)) {
                paper.setRetrievable(true);
            }
        }

    return paper;
}

// sparql - get the title of an event
private String sparqlPaperTitle;

// sparql - papers related to events
private String sparqlRelatedToEvents;

/**
 * The database that is queried
 */
private final Database database;

}
