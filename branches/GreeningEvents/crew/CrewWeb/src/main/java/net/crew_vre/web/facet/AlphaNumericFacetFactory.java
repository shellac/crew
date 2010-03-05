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
package net.crew_vre.web.facet;

import org.caboto.jena.db.Data;

import java.util.Map;
import java.util.List;

/**
 * <p>A factory for creating alpha-numeric facets with a state and possible refinements.</p>
 *
 * @author Mike Jones (mike.a.jones@bristol.ac.uk)
 * @version $Id: AlphaNumericFacetFactory.java 1191 2009-03-31 13:38:51Z cmmaj $
 */
public interface AlphaNumericFacetFactory {

    /**
     * <p>Create a facet with no constraints and is not constrained by other facets.</p>
     *
     * @param config a Map holding the configuration details for the facet.
     * @return a facet implementation with no constraints.
     */
    Facet create(final Map<String, String> config, final Data data);

    /**
     * <p>Creates a facet with no constrants but it constrained by other facets.</p>
     *
     * @param config        a Map holding the configuration details for the facet.
     * @param searchFilters a List of filters that provide constraints.
     * @return a facet implentation with constraints.
     */
    Facet create(final Map<String, String> config, final List<SearchFilter> searchFilters,
                 final Data data);

    /**
     * <p>Creates a fully constrained facet, e.g. "A" has been selected.</p>
     *
     * @param config         a Map holding the configuration details for the facet.
     * @param facetStateName a facet state name
     * @return a representation of a facet that is selected and has no
     *         further refinements.
     */
    Facet create(final Map<String, String> config, final String facetStateName, final Data data);
}
