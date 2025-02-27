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
package net.crew_vre.web.facet.impl;

import net.crew_vre.web.Utility;
import net.crew_vre.web.facet.AlphaNumericFacetFactory;
import net.crew_vre.web.facet.DateTimeFacetFactory;
import net.crew_vre.web.facet.Facet;
import net.crew_vre.web.facet.FacetService;
import net.crew_vre.web.facet.FlatFacetFactory;
import net.crew_vre.web.facet.HierarchicalFacetFactory;
import net.crew_vre.web.facet.SearchFilter;
import org.caboto.jena.db.Data;
import org.caboto.jena.db.DataException;
import org.caboto.jena.db.Database;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> A service implementation that creates facets with states and filters that represent those
 * states in SPARQL.</p>
 *
 * @author Mike Jones (mike.a.jones@bristol.ac.uk)
 * @version $Id: FacetServiceImpl.java 1191 2009-03-31 13:38:51Z cmmaj $
 */
public class FacetServiceImpl implements FacetService {

    private TextFacetFactory tFacetFactory;
	/**
     * <p>Constructor.</p>
     *
     * @param utility        a utilty to deal with URIs in facets.
     * @param anFacetFactory an alpha numeric facet.
     * @param hFacetFactory  an hierarchical facet.
     * @param dtFacetFactory a datetime facet.
     * @param fFacetFactory  a flat facet.
     */
    public FacetServiceImpl(Database database,
                            final Utility utility,
                            final AlphaNumericFacetFactory anFacetFactory,
                            final HierarchicalFacetFactory hFacetFactory,
                            final DateTimeFacetFactory dtFacetFactory,
                            FlatFacetFactory fFacetFactory,
                            final TextFacetFactory tFacetFactory) {
        this.database = database;
        this.utility = utility;
        this.anFacetFactory = anFacetFactory;
        this.hFacetFactory = hFacetFactory;
        this.dtFacetFactory = dtFacetFactory;
        this.fFacetFactory = fFacetFactory;
        this.tFacetFactory = tFacetFactory;
    }

    /**
     * <p>We need to check the request parameters to see what is the current state of the
     * facets. To do this, we iterate over each of the facet configurations and find out
     * what value is used in the request parameter names. It checks if that value exists
     * in the request - if yes, checks the facet type and creates the appropriate
     * search filter.</p>
     *
     * @param facetConfigs the facet configuration
     * @param request      the HTTP request
     * @return a list a search filter objects
     */
    public List<SearchFilter> generateSearchFilters(List<Map<String, String>> facetConfigs,
                                                    HttpServletRequest request) {

        // holds the search filters
        List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();

        // iterate over the list of facet configurations...
        for (Map<String, String> config : facetConfigs) {

            // get the parameter name and the facet type
            String paramName = config.get(Facet.PARAM_NAME);
            String facetType = config.get(Facet.FACET_TYPE);

            // if the parameter name used by this facet is found in the request ...
            if (request.getParameter(paramName) != null) {

                String parameter = request.getParameter(paramName);
                SearchFilter filter = null;

                // check the type and create the correct filter type
                if (facetType.equals(Facet.ALPHA_NUMERIC_FACET_TYPE)) {

                    filter = new AlphaNumericSearchFilterImpl(config.get(Facet.PARAM_NAME),
                            config.get(Facet.LINK_PROPERTY), parameter);

                } else if (facetType.equals(Facet.HIERARCHICAL_FACET_TYPE)) {

                    String uri = utility.parameterValueToUri(parameter);
                    filter = new HierarchicalSearchFilterImpl(config.get(Facet.LINK_PROPERTY), uri);
                } else if (facetType.equals(Facet.DATE_TIME_FACET_TYPE)) {

                    filter = new DateTimeSearchFilterImpl(config.get(Facet.PARAM_NAME),
                            config.get(Facet.LINK_PROPERTY), parameter);

                } else if (facetType.equals(Facet.FLAT_FACET_TYPE)) {

                    String uri = utility.parameterValueToUri(request.getParameter(paramName));

                    filter = new FlatSearchFilterImpl(config.get(Facet.LINK_PROPERTY), uri);
                } else if (facetType.equals(Facet.TEXT_SEARCH_FACET)) {
                	filter = new TextSearchFilter(request.getParameter(paramName));
                }


                if (filter != null) {
                    searchFilters.add(filter);
                }
            }
        }

        return searchFilters;
    }

    /**
     * <p>Generate a list of facets with states.</p>
     *
     * @param facetConfigs the facet configuration
     * @return a list of facets.
     */
    public List<Facet> generateStates(List<Map<String, String>> facetConfigs) {
        return generateInitialStates(facetConfigs);
    }

    /**
     * <p>Generate a list of facets with states.</p>
     *
     * @param facetConfigs  the facet configuration
     * @param request       the http request.
     * @param searchFilters filters to constrain the facet states.
     * @return a list of facts.
     */
    public List<Facet> generateStates(List<Map<String, String>> facetConfigs,
                                      HttpServletRequest request,
                                      List<SearchFilter> searchFilters) {

        Data data;

        try {
            data = database.getData();
        } catch (DataException ex) {
            throw new RuntimeException(ex.getCause());
        }

        List<Facet> facets = new ArrayList<Facet>();

        for (Map<String, String> config : facetConfigs) {

            Facet facet = null;

            if (config.get(Facet.FACET_TYPE).equals(Facet.ALPHA_NUMERIC_FACET_TYPE)) {

                if (request.getParameter(config.get(Facet.PARAM_NAME)) != null) {

                    facet = anFacetFactory.create(config,
                            request.getParameter(config.get(Facet.PARAM_NAME)), data);
                } else {

                    facet = anFacetFactory.create(config, searchFilters, data);
                }

            } else if (config.get(Facet.FACET_TYPE).equals(Facet.HIERARCHICAL_FACET_TYPE)) {

                if (request.getParameter(config.get(Facet.PARAM_NAME)) != null) {

                    facet = hFacetFactory.create(config, searchFilters,
                            request.getParameter(config.get(Facet.PARAM_NAME)), data);
                } else {

                    facet = hFacetFactory.create(config, searchFilters, data);
                }

            } else if (config.get(Facet.FACET_TYPE).equals(Facet.DATE_TIME_FACET_TYPE)) {

                if (request.getParameter(config.get(Facet.PARAM_NAME)) != null) {
                    facet = dtFacetFactory.create(config, searchFilters,
                            request.getParameter(config.get(Facet.PARAM_NAME)), data);
                } else {
                    facet = dtFacetFactory.create(config, searchFilters, data);
                }

            } else if (config.get(Facet.FACET_TYPE).equals(Facet.FLAT_FACET_TYPE)) {

                if (request.getParameter(config.get(Facet.PARAM_NAME)) != null) {
                    facet = fFacetFactory.create(config,
                            request.getParameter(config.get(Facet.PARAM_NAME)), data);
                } else {
                    facet = fFacetFactory.create(config, searchFilters, data);
                }
            } else if (config.get(Facet.FACET_TYPE).equals(Facet.TEXT_SEARCH_FACET)) {
            	if (request.getParameter(config.get(Facet.PARAM_NAME)) != null) {
                    facet = tFacetFactory.create(config,
                            request.getParameter(config.get(Facet.PARAM_NAME)), data);
                } else {
                    facet = tFacetFactory.create(config, searchFilters, data);
                }
            }
            facets.add(facet);
        }

        if (data != null) {
            data.close();
        }

        return facets;
    }


    /**
     * @param facetConfigs the facet configuration
     * @return a list of facets in their initial state.
     */
    private List<Facet> generateInitialStates(List<Map<String, String>> facetConfigs) {

        Data data;

        try {
            data = database.getData();
        } catch (DataException ex) {
            throw new RuntimeException(ex.getCause());
        }

        List<Facet> facets = new ArrayList<Facet>();

        for (Map<String, String> config : facetConfigs) {

            Facet facet = null;

            if (config.get(Facet.FACET_TYPE).equals(Facet.ALPHA_NUMERIC_FACET_TYPE)) {
                long start = System.currentTimeMillis();
                //System.out.println("ALPHA NUMERIC");
                facet = anFacetFactory.create(config, data);
                //System.out.println("Alpha Numeric: " + (System.currentTimeMillis() - start));
            } else if (config.get(Facet.FACET_TYPE).equals(Facet.HIERARCHICAL_FACET_TYPE)) {
                //System.out.println("HIERARCHICAL");
                long start = System.currentTimeMillis();
                facet = hFacetFactory.create(config, data);
                //System.out.println("Hierarchical: " + (System.currentTimeMillis() - start));
            } else if (config.get(Facet.FACET_TYPE).equals(Facet.DATE_TIME_FACET_TYPE)) {
                //System.out.println("DATE TIME");
                long start = System.currentTimeMillis();
                facet = dtFacetFactory.create(config, data);
                //System.out.println("Date Time: " + (System.currentTimeMillis() - start));
            } else if (config.get(Facet.FACET_TYPE).equals(Facet.FLAT_FACET_TYPE)) {
                long start = System.currentTimeMillis();
                //System.out.println("FLAT");
                facet = fFacetFactory.create(config, data);
                //System.out.println("Flat: " + (System.currentTimeMillis() - start));
            } else if (config.get(Facet.FACET_TYPE).equals(Facet.TEXT_SEARCH_FACET)) {
            	facet = tFacetFactory.create(config, data);
            }
            facets.add(facet);
        }

        if (data != null) {
            data.close();
        }

        return facets;
    }

    private Utility utility;
    private AlphaNumericFacetFactory anFacetFactory;
    private HierarchicalFacetFactory hFacetFactory;
    private DateTimeFacetFactory dtFacetFactory;
    private FlatFacetFactory fFacetFactory;
    private Database database;
}
