package com.mma.mma.web.rest;

import com.mma.mma.MmaApp;

import com.mma.mma.domain.Pubgitem;
import com.mma.mma.repository.PubgitemRepository;
import com.mma.mma.service.PubgitemService;
import com.mma.mma.repository.search.PubgitemSearchRepository;
import com.mma.mma.service.dto.PubgitemDTO;
import com.mma.mma.service.mapper.PubgitemMapper;
import com.mma.mma.web.rest.errors.ExceptionTranslator;
import com.mma.mma.service.dto.PubgitemCriteria;
import com.mma.mma.service.PubgitemQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PubgitemResource REST controller.
 *
 * @see PubgitemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MmaApp.class)
public class PubgitemResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UNS = false;
    private static final Boolean UPDATED_UNS = true;

    private static final String DEFAULT_UNSR = "AAAAAAAAAA";
    private static final String UPDATED_UNSR = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Double DEFAULT_SP = 1D;
    private static final Double UPDATED_SP = 2D;

    private static final Double DEFAULT_MAXP = 1D;
    private static final Double UPDATED_MAXP = 2D;

    private static final Double DEFAULT_AVGP = 1D;
    private static final Double UPDATED_AVGP = 2D;

    private static final Double DEFAULT_MINP = 1D;
    private static final Double UPDATED_MINP = 2D;

    private static final Integer DEFAULT_SAVGD = 1;
    private static final Integer UPDATED_SAVGD = 2;

    private static final Integer DEFAULT_S_24_H = 1;
    private static final Integer UPDATED_S_24_H = 2;

    private static final Integer DEFAULT_S_7_D = 1;
    private static final Integer UPDATED_S_7_D = 2;

    private static final Integer DEFAULT_S_30_D = 1;
    private static final Integer UPDATED_S_30_D = 2;

    private static final Integer DEFAULT_S_90_D = 1;
    private static final Integer UPDATED_S_90_D = 2;

    private static final Double DEFAULT_CFP = 1D;
    private static final Double UPDATED_CFP = 2D;

    private static final Double DEFAULT_IOP = 1D;
    private static final Double UPDATED_IOP = 2D;

    private static final Double DEFAULT_DCX = 1D;
    private static final Double UPDATED_DCX = 2D;

    private static final Double DEFAULT_DOPX = 1D;
    private static final Double UPDATED_DOPX = 2D;

    private static final Double DEFAULT_OPLP = 1D;
    private static final Double UPDATED_OPLP = 2D;

    private static final Integer DEFAULT_OPQ = 1;
    private static final Integer UPDATED_OPQ = 2;

    private static final String DEFAULT_NID = "AAAAAAAAAA";
    private static final String UPDATED_NID = "BBBBBBBBBB";

    private static final Instant DEFAULT_UAT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UAT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PubgitemRepository pubgitemRepository;

    @Autowired
    private PubgitemMapper pubgitemMapper;

    @Autowired
    private PubgitemService pubgitemService;

    @Autowired
    private PubgitemSearchRepository pubgitemSearchRepository;

    @Autowired
    private PubgitemQueryService pubgitemQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPubgitemMockMvc;

    private Pubgitem pubgitem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PubgitemResource pubgitemResource = new PubgitemResource(pubgitemService, pubgitemQueryService);
        this.restPubgitemMockMvc = MockMvcBuilders.standaloneSetup(pubgitemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pubgitem createEntity(EntityManager em) {
        Pubgitem pubgitem = new Pubgitem()
            .name(DEFAULT_NAME)
            .uns(DEFAULT_UNS)
            .unsr(DEFAULT_UNSR)
            .rank(DEFAULT_RANK)
            .sp(DEFAULT_SP)
            .maxp(DEFAULT_MAXP)
            .avgp(DEFAULT_AVGP)
            .minp(DEFAULT_MINP)
            .savgd(DEFAULT_SAVGD)
            .s24h(DEFAULT_S_24_H)
            .s7d(DEFAULT_S_7_D)
            .s30d(DEFAULT_S_30_D)
            .s90d(DEFAULT_S_90_D)
            .cfp(DEFAULT_CFP)
            .iop(DEFAULT_IOP)
            .dcx(DEFAULT_DCX)
            .dopx(DEFAULT_DOPX)
            .oplp(DEFAULT_OPLP)
            .opq(DEFAULT_OPQ)
            .nid(DEFAULT_NID)
            .uat(DEFAULT_UAT);
        return pubgitem;
    }

    @Before
    public void initTest() {
        pubgitemSearchRepository.deleteAll();
        pubgitem = createEntity(em);
    }

    @Test
    @Transactional
    public void createPubgitem() throws Exception {
        int databaseSizeBeforeCreate = pubgitemRepository.findAll().size();

        // Create the Pubgitem
        PubgitemDTO pubgitemDTO = pubgitemMapper.toDto(pubgitem);
        restPubgitemMockMvc.perform(post("/api/pubgitems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pubgitemDTO)))
            .andExpect(status().isCreated());

        // Validate the Pubgitem in the database
        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeCreate + 1);
        Pubgitem testPubgitem = pubgitemList.get(pubgitemList.size() - 1);
        assertThat(testPubgitem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPubgitem.isUns()).isEqualTo(DEFAULT_UNS);
        assertThat(testPubgitem.getUnsr()).isEqualTo(DEFAULT_UNSR);
        assertThat(testPubgitem.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testPubgitem.getSp()).isEqualTo(DEFAULT_SP);
        assertThat(testPubgitem.getMaxp()).isEqualTo(DEFAULT_MAXP);
        assertThat(testPubgitem.getAvgp()).isEqualTo(DEFAULT_AVGP);
        assertThat(testPubgitem.getMinp()).isEqualTo(DEFAULT_MINP);
        assertThat(testPubgitem.getSavgd()).isEqualTo(DEFAULT_SAVGD);
        assertThat(testPubgitem.gets24h()).isEqualTo(DEFAULT_S_24_H);
        assertThat(testPubgitem.gets7d()).isEqualTo(DEFAULT_S_7_D);
        assertThat(testPubgitem.gets30d()).isEqualTo(DEFAULT_S_30_D);
        assertThat(testPubgitem.gets90d()).isEqualTo(DEFAULT_S_90_D);
        assertThat(testPubgitem.getCfp()).isEqualTo(DEFAULT_CFP);
        assertThat(testPubgitem.getIop()).isEqualTo(DEFAULT_IOP);
        assertThat(testPubgitem.getDcx()).isEqualTo(DEFAULT_DCX);
        assertThat(testPubgitem.getDopx()).isEqualTo(DEFAULT_DOPX);
        assertThat(testPubgitem.getOplp()).isEqualTo(DEFAULT_OPLP);
        assertThat(testPubgitem.getOpq()).isEqualTo(DEFAULT_OPQ);
        assertThat(testPubgitem.getNid()).isEqualTo(DEFAULT_NID);
        assertThat(testPubgitem.getUat()).isEqualTo(DEFAULT_UAT);

        // Validate the Pubgitem in Elasticsearch
        Pubgitem pubgitemEs = pubgitemSearchRepository.findOne(testPubgitem.getId());
        assertThat(pubgitemEs).isEqualToComparingFieldByField(testPubgitem);
    }

    @Test
    @Transactional
    public void createPubgitemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pubgitemRepository.findAll().size();

        // Create the Pubgitem with an existing ID
        pubgitem.setId(1L);
        PubgitemDTO pubgitemDTO = pubgitemMapper.toDto(pubgitem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPubgitemMockMvc.perform(post("/api/pubgitems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pubgitemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pubgitem in the database
        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pubgitemRepository.findAll().size();
        // set the field null
        pubgitem.setName(null);

        // Create the Pubgitem, which fails.
        PubgitemDTO pubgitemDTO = pubgitemMapper.toDto(pubgitem);

        restPubgitemMockMvc.perform(post("/api/pubgitems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pubgitemDTO)))
            .andExpect(status().isBadRequest());

        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPubgitems() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList
        restPubgitemMockMvc.perform(get("/api/pubgitems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pubgitem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].uns").value(hasItem(DEFAULT_UNS.booleanValue())))
            .andExpect(jsonPath("$.[*].unsr").value(hasItem(DEFAULT_UNSR.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.doubleValue())))
            .andExpect(jsonPath("$.[*].maxp").value(hasItem(DEFAULT_MAXP.doubleValue())))
            .andExpect(jsonPath("$.[*].avgp").value(hasItem(DEFAULT_AVGP.doubleValue())))
            .andExpect(jsonPath("$.[*].minp").value(hasItem(DEFAULT_MINP.doubleValue())))
            .andExpect(jsonPath("$.[*].savgd").value(hasItem(DEFAULT_SAVGD)))
            .andExpect(jsonPath("$.[*].s24h").value(hasItem(DEFAULT_S_24_H)))
            .andExpect(jsonPath("$.[*].s7d").value(hasItem(DEFAULT_S_7_D)))
            .andExpect(jsonPath("$.[*].s30d").value(hasItem(DEFAULT_S_30_D)))
            .andExpect(jsonPath("$.[*].s90d").value(hasItem(DEFAULT_S_90_D)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].dopx").value(hasItem(DEFAULT_DOPX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].nid").value(hasItem(DEFAULT_NID.toString())))
            .andExpect(jsonPath("$.[*].uat").value(hasItem(DEFAULT_UAT.toString())));
    }

    @Test
    @Transactional
    public void getPubgitem() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get the pubgitem
        restPubgitemMockMvc.perform(get("/api/pubgitems/{id}", pubgitem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pubgitem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.uns").value(DEFAULT_UNS.booleanValue()))
            .andExpect(jsonPath("$.unsr").value(DEFAULT_UNSR.toString()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.sp").value(DEFAULT_SP.doubleValue()))
            .andExpect(jsonPath("$.maxp").value(DEFAULT_MAXP.doubleValue()))
            .andExpect(jsonPath("$.avgp").value(DEFAULT_AVGP.doubleValue()))
            .andExpect(jsonPath("$.minp").value(DEFAULT_MINP.doubleValue()))
            .andExpect(jsonPath("$.savgd").value(DEFAULT_SAVGD))
            .andExpect(jsonPath("$.s24h").value(DEFAULT_S_24_H))
            .andExpect(jsonPath("$.s7d").value(DEFAULT_S_7_D))
            .andExpect(jsonPath("$.s30d").value(DEFAULT_S_30_D))
            .andExpect(jsonPath("$.s90d").value(DEFAULT_S_90_D))
            .andExpect(jsonPath("$.cfp").value(DEFAULT_CFP.doubleValue()))
            .andExpect(jsonPath("$.iop").value(DEFAULT_IOP.doubleValue()))
            .andExpect(jsonPath("$.dcx").value(DEFAULT_DCX.doubleValue()))
            .andExpect(jsonPath("$.dopx").value(DEFAULT_DOPX.doubleValue()))
            .andExpect(jsonPath("$.oplp").value(DEFAULT_OPLP.doubleValue()))
            .andExpect(jsonPath("$.opq").value(DEFAULT_OPQ))
            .andExpect(jsonPath("$.nid").value(DEFAULT_NID.toString()))
            .andExpect(jsonPath("$.uat").value(DEFAULT_UAT.toString()));
    }

    @Test
    @Transactional
    public void getAllPubgitemsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where name equals to DEFAULT_NAME
        defaultPubgitemShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pubgitemList where name equals to UPDATED_NAME
        defaultPubgitemShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPubgitemShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pubgitemList where name equals to UPDATED_NAME
        defaultPubgitemShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where name is not null
        defaultPubgitemShouldBeFound("name.specified=true");

        // Get all the pubgitemList where name is null
        defaultPubgitemShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uns equals to DEFAULT_UNS
        defaultPubgitemShouldBeFound("uns.equals=" + DEFAULT_UNS);

        // Get all the pubgitemList where uns equals to UPDATED_UNS
        defaultPubgitemShouldNotBeFound("uns.equals=" + UPDATED_UNS);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uns in DEFAULT_UNS or UPDATED_UNS
        defaultPubgitemShouldBeFound("uns.in=" + DEFAULT_UNS + "," + UPDATED_UNS);

        // Get all the pubgitemList where uns equals to UPDATED_UNS
        defaultPubgitemShouldNotBeFound("uns.in=" + UPDATED_UNS);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uns is not null
        defaultPubgitemShouldBeFound("uns.specified=true");

        // Get all the pubgitemList where uns is null
        defaultPubgitemShouldNotBeFound("uns.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsrIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where unsr equals to DEFAULT_UNSR
        defaultPubgitemShouldBeFound("unsr.equals=" + DEFAULT_UNSR);

        // Get all the pubgitemList where unsr equals to UPDATED_UNSR
        defaultPubgitemShouldNotBeFound("unsr.equals=" + UPDATED_UNSR);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsrIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where unsr in DEFAULT_UNSR or UPDATED_UNSR
        defaultPubgitemShouldBeFound("unsr.in=" + DEFAULT_UNSR + "," + UPDATED_UNSR);

        // Get all the pubgitemList where unsr equals to UPDATED_UNSR
        defaultPubgitemShouldNotBeFound("unsr.in=" + UPDATED_UNSR);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUnsrIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where unsr is not null
        defaultPubgitemShouldBeFound("unsr.specified=true");

        // Get all the pubgitemList where unsr is null
        defaultPubgitemShouldNotBeFound("unsr.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByRankIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where rank equals to DEFAULT_RANK
        defaultPubgitemShouldBeFound("rank.equals=" + DEFAULT_RANK);

        // Get all the pubgitemList where rank equals to UPDATED_RANK
        defaultPubgitemShouldNotBeFound("rank.equals=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByRankIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where rank in DEFAULT_RANK or UPDATED_RANK
        defaultPubgitemShouldBeFound("rank.in=" + DEFAULT_RANK + "," + UPDATED_RANK);

        // Get all the pubgitemList where rank equals to UPDATED_RANK
        defaultPubgitemShouldNotBeFound("rank.in=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByRankIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where rank is not null
        defaultPubgitemShouldBeFound("rank.specified=true");

        // Get all the pubgitemList where rank is null
        defaultPubgitemShouldNotBeFound("rank.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByRankIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where rank greater than or equals to DEFAULT_RANK
        defaultPubgitemShouldBeFound("rank.greaterOrEqualThan=" + DEFAULT_RANK);

        // Get all the pubgitemList where rank greater than or equals to UPDATED_RANK
        defaultPubgitemShouldNotBeFound("rank.greaterOrEqualThan=" + UPDATED_RANK);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByRankIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where rank less than or equals to DEFAULT_RANK
        defaultPubgitemShouldNotBeFound("rank.lessThan=" + DEFAULT_RANK);

        // Get all the pubgitemList where rank less than or equals to UPDATED_RANK
        defaultPubgitemShouldBeFound("rank.lessThan=" + UPDATED_RANK);
    }


    @Test
    @Transactional
    public void getAllPubgitemsBySpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where sp equals to DEFAULT_SP
        defaultPubgitemShouldBeFound("sp.equals=" + DEFAULT_SP);

        // Get all the pubgitemList where sp equals to UPDATED_SP
        defaultPubgitemShouldNotBeFound("sp.equals=" + UPDATED_SP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where sp in DEFAULT_SP or UPDATED_SP
        defaultPubgitemShouldBeFound("sp.in=" + DEFAULT_SP + "," + UPDATED_SP);

        // Get all the pubgitemList where sp equals to UPDATED_SP
        defaultPubgitemShouldNotBeFound("sp.in=" + UPDATED_SP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where sp is not null
        defaultPubgitemShouldBeFound("sp.specified=true");

        // Get all the pubgitemList where sp is null
        defaultPubgitemShouldNotBeFound("sp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMaxpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where maxp equals to DEFAULT_MAXP
        defaultPubgitemShouldBeFound("maxp.equals=" + DEFAULT_MAXP);

        // Get all the pubgitemList where maxp equals to UPDATED_MAXP
        defaultPubgitemShouldNotBeFound("maxp.equals=" + UPDATED_MAXP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMaxpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where maxp in DEFAULT_MAXP or UPDATED_MAXP
        defaultPubgitemShouldBeFound("maxp.in=" + DEFAULT_MAXP + "," + UPDATED_MAXP);

        // Get all the pubgitemList where maxp equals to UPDATED_MAXP
        defaultPubgitemShouldNotBeFound("maxp.in=" + UPDATED_MAXP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMaxpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where maxp is not null
        defaultPubgitemShouldBeFound("maxp.specified=true");

        // Get all the pubgitemList where maxp is null
        defaultPubgitemShouldNotBeFound("maxp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByAvgpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where avgp equals to DEFAULT_AVGP
        defaultPubgitemShouldBeFound("avgp.equals=" + DEFAULT_AVGP);

        // Get all the pubgitemList where avgp equals to UPDATED_AVGP
        defaultPubgitemShouldNotBeFound("avgp.equals=" + UPDATED_AVGP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByAvgpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where avgp in DEFAULT_AVGP or UPDATED_AVGP
        defaultPubgitemShouldBeFound("avgp.in=" + DEFAULT_AVGP + "," + UPDATED_AVGP);

        // Get all the pubgitemList where avgp equals to UPDATED_AVGP
        defaultPubgitemShouldNotBeFound("avgp.in=" + UPDATED_AVGP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByAvgpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where avgp is not null
        defaultPubgitemShouldBeFound("avgp.specified=true");

        // Get all the pubgitemList where avgp is null
        defaultPubgitemShouldNotBeFound("avgp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMinpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where minp equals to DEFAULT_MINP
        defaultPubgitemShouldBeFound("minp.equals=" + DEFAULT_MINP);

        // Get all the pubgitemList where minp equals to UPDATED_MINP
        defaultPubgitemShouldNotBeFound("minp.equals=" + UPDATED_MINP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMinpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where minp in DEFAULT_MINP or UPDATED_MINP
        defaultPubgitemShouldBeFound("minp.in=" + DEFAULT_MINP + "," + UPDATED_MINP);

        // Get all the pubgitemList where minp equals to UPDATED_MINP
        defaultPubgitemShouldNotBeFound("minp.in=" + UPDATED_MINP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByMinpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where minp is not null
        defaultPubgitemShouldBeFound("minp.specified=true");

        // Get all the pubgitemList where minp is null
        defaultPubgitemShouldNotBeFound("minp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySavgdIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where savgd equals to DEFAULT_SAVGD
        defaultPubgitemShouldBeFound("savgd.equals=" + DEFAULT_SAVGD);

        // Get all the pubgitemList where savgd equals to UPDATED_SAVGD
        defaultPubgitemShouldNotBeFound("savgd.equals=" + UPDATED_SAVGD);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySavgdIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where savgd in DEFAULT_SAVGD or UPDATED_SAVGD
        defaultPubgitemShouldBeFound("savgd.in=" + DEFAULT_SAVGD + "," + UPDATED_SAVGD);

        // Get all the pubgitemList where savgd equals to UPDATED_SAVGD
        defaultPubgitemShouldNotBeFound("savgd.in=" + UPDATED_SAVGD);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySavgdIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where savgd is not null
        defaultPubgitemShouldBeFound("savgd.specified=true");

        // Get all the pubgitemList where savgd is null
        defaultPubgitemShouldNotBeFound("savgd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySavgdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where savgd greater than or equals to DEFAULT_SAVGD
        defaultPubgitemShouldBeFound("savgd.greaterOrEqualThan=" + DEFAULT_SAVGD);

        // Get all the pubgitemList where savgd greater than or equals to UPDATED_SAVGD
        defaultPubgitemShouldNotBeFound("savgd.greaterOrEqualThan=" + UPDATED_SAVGD);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBySavgdIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where savgd less than or equals to DEFAULT_SAVGD
        defaultPubgitemShouldNotBeFound("savgd.lessThan=" + DEFAULT_SAVGD);

        // Get all the pubgitemList where savgd less than or equals to UPDATED_SAVGD
        defaultPubgitemShouldBeFound("savgd.lessThan=" + UPDATED_SAVGD);
    }


    @Test
    @Transactional
    public void getAllPubgitemsBys24hIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s24h equals to DEFAULT_S_24_H
        defaultPubgitemShouldBeFound("s24h.equals=" + DEFAULT_S_24_H);

        // Get all the pubgitemList where s24h equals to UPDATED_S_24_H
        defaultPubgitemShouldNotBeFound("s24h.equals=" + UPDATED_S_24_H);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys24hIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s24h in DEFAULT_S_24_H or UPDATED_S_24_H
        defaultPubgitemShouldBeFound("s24h.in=" + DEFAULT_S_24_H + "," + UPDATED_S_24_H);

        // Get all the pubgitemList where s24h equals to UPDATED_S_24_H
        defaultPubgitemShouldNotBeFound("s24h.in=" + UPDATED_S_24_H);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys24hIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s24h is not null
        defaultPubgitemShouldBeFound("s24h.specified=true");

        // Get all the pubgitemList where s24h is null
        defaultPubgitemShouldNotBeFound("s24h.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys24hIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s24h greater than or equals to DEFAULT_S_24_H
        defaultPubgitemShouldBeFound("s24h.greaterOrEqualThan=" + DEFAULT_S_24_H);

        // Get all the pubgitemList where s24h greater than or equals to UPDATED_S_24_H
        defaultPubgitemShouldNotBeFound("s24h.greaterOrEqualThan=" + UPDATED_S_24_H);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys24hIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s24h less than or equals to DEFAULT_S_24_H
        defaultPubgitemShouldNotBeFound("s24h.lessThan=" + DEFAULT_S_24_H);

        // Get all the pubgitemList where s24h less than or equals to UPDATED_S_24_H
        defaultPubgitemShouldBeFound("s24h.lessThan=" + UPDATED_S_24_H);
    }


    @Test
    @Transactional
    public void getAllPubgitemsBys7dIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s7d equals to DEFAULT_S_7_D
        defaultPubgitemShouldBeFound("s7d.equals=" + DEFAULT_S_7_D);

        // Get all the pubgitemList where s7d equals to UPDATED_S_7_D
        defaultPubgitemShouldNotBeFound("s7d.equals=" + UPDATED_S_7_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys7dIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s7d in DEFAULT_S_7_D or UPDATED_S_7_D
        defaultPubgitemShouldBeFound("s7d.in=" + DEFAULT_S_7_D + "," + UPDATED_S_7_D);

        // Get all the pubgitemList where s7d equals to UPDATED_S_7_D
        defaultPubgitemShouldNotBeFound("s7d.in=" + UPDATED_S_7_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys7dIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s7d is not null
        defaultPubgitemShouldBeFound("s7d.specified=true");

        // Get all the pubgitemList where s7d is null
        defaultPubgitemShouldNotBeFound("s7d.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys7dIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s7d greater than or equals to DEFAULT_S_7_D
        defaultPubgitemShouldBeFound("s7d.greaterOrEqualThan=" + DEFAULT_S_7_D);

        // Get all the pubgitemList where s7d greater than or equals to UPDATED_S_7_D
        defaultPubgitemShouldNotBeFound("s7d.greaterOrEqualThan=" + UPDATED_S_7_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys7dIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s7d less than or equals to DEFAULT_S_7_D
        defaultPubgitemShouldNotBeFound("s7d.lessThan=" + DEFAULT_S_7_D);

        // Get all the pubgitemList where s7d less than or equals to UPDATED_S_7_D
        defaultPubgitemShouldBeFound("s7d.lessThan=" + UPDATED_S_7_D);
    }


    @Test
    @Transactional
    public void getAllPubgitemsBys30dIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s30d equals to DEFAULT_S_30_D
        defaultPubgitemShouldBeFound("s30d.equals=" + DEFAULT_S_30_D);

        // Get all the pubgitemList where s30d equals to UPDATED_S_30_D
        defaultPubgitemShouldNotBeFound("s30d.equals=" + UPDATED_S_30_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys30dIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s30d in DEFAULT_S_30_D or UPDATED_S_30_D
        defaultPubgitemShouldBeFound("s30d.in=" + DEFAULT_S_30_D + "," + UPDATED_S_30_D);

        // Get all the pubgitemList where s30d equals to UPDATED_S_30_D
        defaultPubgitemShouldNotBeFound("s30d.in=" + UPDATED_S_30_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys30dIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s30d is not null
        defaultPubgitemShouldBeFound("s30d.specified=true");

        // Get all the pubgitemList where s30d is null
        defaultPubgitemShouldNotBeFound("s30d.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys30dIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s30d greater than or equals to DEFAULT_S_30_D
        defaultPubgitemShouldBeFound("s30d.greaterOrEqualThan=" + DEFAULT_S_30_D);

        // Get all the pubgitemList where s30d greater than or equals to UPDATED_S_30_D
        defaultPubgitemShouldNotBeFound("s30d.greaterOrEqualThan=" + UPDATED_S_30_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys30dIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s30d less than or equals to DEFAULT_S_30_D
        defaultPubgitemShouldNotBeFound("s30d.lessThan=" + DEFAULT_S_30_D);

        // Get all the pubgitemList where s30d less than or equals to UPDATED_S_30_D
        defaultPubgitemShouldBeFound("s30d.lessThan=" + UPDATED_S_30_D);
    }


    @Test
    @Transactional
    public void getAllPubgitemsBys90dIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s90d equals to DEFAULT_S_90_D
        defaultPubgitemShouldBeFound("s90d.equals=" + DEFAULT_S_90_D);

        // Get all the pubgitemList where s90d equals to UPDATED_S_90_D
        defaultPubgitemShouldNotBeFound("s90d.equals=" + UPDATED_S_90_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys90dIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s90d in DEFAULT_S_90_D or UPDATED_S_90_D
        defaultPubgitemShouldBeFound("s90d.in=" + DEFAULT_S_90_D + "," + UPDATED_S_90_D);

        // Get all the pubgitemList where s90d equals to UPDATED_S_90_D
        defaultPubgitemShouldNotBeFound("s90d.in=" + UPDATED_S_90_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys90dIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s90d is not null
        defaultPubgitemShouldBeFound("s90d.specified=true");

        // Get all the pubgitemList where s90d is null
        defaultPubgitemShouldNotBeFound("s90d.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys90dIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s90d greater than or equals to DEFAULT_S_90_D
        defaultPubgitemShouldBeFound("s90d.greaterOrEqualThan=" + DEFAULT_S_90_D);

        // Get all the pubgitemList where s90d greater than or equals to UPDATED_S_90_D
        defaultPubgitemShouldNotBeFound("s90d.greaterOrEqualThan=" + UPDATED_S_90_D);
    }

    @Test
    @Transactional
    public void getAllPubgitemsBys90dIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where s90d less than or equals to DEFAULT_S_90_D
        defaultPubgitemShouldNotBeFound("s90d.lessThan=" + DEFAULT_S_90_D);

        // Get all the pubgitemList where s90d less than or equals to UPDATED_S_90_D
        defaultPubgitemShouldBeFound("s90d.lessThan=" + UPDATED_S_90_D);
    }


    @Test
    @Transactional
    public void getAllPubgitemsByCfpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where cfp equals to DEFAULT_CFP
        defaultPubgitemShouldBeFound("cfp.equals=" + DEFAULT_CFP);

        // Get all the pubgitemList where cfp equals to UPDATED_CFP
        defaultPubgitemShouldNotBeFound("cfp.equals=" + UPDATED_CFP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByCfpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where cfp in DEFAULT_CFP or UPDATED_CFP
        defaultPubgitemShouldBeFound("cfp.in=" + DEFAULT_CFP + "," + UPDATED_CFP);

        // Get all the pubgitemList where cfp equals to UPDATED_CFP
        defaultPubgitemShouldNotBeFound("cfp.in=" + UPDATED_CFP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByCfpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where cfp is not null
        defaultPubgitemShouldBeFound("cfp.specified=true");

        // Get all the pubgitemList where cfp is null
        defaultPubgitemShouldNotBeFound("cfp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByIopIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where iop equals to DEFAULT_IOP
        defaultPubgitemShouldBeFound("iop.equals=" + DEFAULT_IOP);

        // Get all the pubgitemList where iop equals to UPDATED_IOP
        defaultPubgitemShouldNotBeFound("iop.equals=" + UPDATED_IOP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByIopIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where iop in DEFAULT_IOP or UPDATED_IOP
        defaultPubgitemShouldBeFound("iop.in=" + DEFAULT_IOP + "," + UPDATED_IOP);

        // Get all the pubgitemList where iop equals to UPDATED_IOP
        defaultPubgitemShouldNotBeFound("iop.in=" + UPDATED_IOP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByIopIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where iop is not null
        defaultPubgitemShouldBeFound("iop.specified=true");

        // Get all the pubgitemList where iop is null
        defaultPubgitemShouldNotBeFound("iop.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDcxIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dcx equals to DEFAULT_DCX
        defaultPubgitemShouldBeFound("dcx.equals=" + DEFAULT_DCX);

        // Get all the pubgitemList where dcx equals to UPDATED_DCX
        defaultPubgitemShouldNotBeFound("dcx.equals=" + UPDATED_DCX);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDcxIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dcx in DEFAULT_DCX or UPDATED_DCX
        defaultPubgitemShouldBeFound("dcx.in=" + DEFAULT_DCX + "," + UPDATED_DCX);

        // Get all the pubgitemList where dcx equals to UPDATED_DCX
        defaultPubgitemShouldNotBeFound("dcx.in=" + UPDATED_DCX);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDcxIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dcx is not null
        defaultPubgitemShouldBeFound("dcx.specified=true");

        // Get all the pubgitemList where dcx is null
        defaultPubgitemShouldNotBeFound("dcx.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDopxIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dopx equals to DEFAULT_DOPX
        defaultPubgitemShouldBeFound("dopx.equals=" + DEFAULT_DOPX);

        // Get all the pubgitemList where dopx equals to UPDATED_DOPX
        defaultPubgitemShouldNotBeFound("dopx.equals=" + UPDATED_DOPX);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDopxIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dopx in DEFAULT_DOPX or UPDATED_DOPX
        defaultPubgitemShouldBeFound("dopx.in=" + DEFAULT_DOPX + "," + UPDATED_DOPX);

        // Get all the pubgitemList where dopx equals to UPDATED_DOPX
        defaultPubgitemShouldNotBeFound("dopx.in=" + UPDATED_DOPX);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByDopxIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where dopx is not null
        defaultPubgitemShouldBeFound("dopx.specified=true");

        // Get all the pubgitemList where dopx is null
        defaultPubgitemShouldNotBeFound("dopx.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOplpIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where oplp equals to DEFAULT_OPLP
        defaultPubgitemShouldBeFound("oplp.equals=" + DEFAULT_OPLP);

        // Get all the pubgitemList where oplp equals to UPDATED_OPLP
        defaultPubgitemShouldNotBeFound("oplp.equals=" + UPDATED_OPLP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOplpIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where oplp in DEFAULT_OPLP or UPDATED_OPLP
        defaultPubgitemShouldBeFound("oplp.in=" + DEFAULT_OPLP + "," + UPDATED_OPLP);

        // Get all the pubgitemList where oplp equals to UPDATED_OPLP
        defaultPubgitemShouldNotBeFound("oplp.in=" + UPDATED_OPLP);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOplpIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where oplp is not null
        defaultPubgitemShouldBeFound("oplp.specified=true");

        // Get all the pubgitemList where oplp is null
        defaultPubgitemShouldNotBeFound("oplp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOpqIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where opq equals to DEFAULT_OPQ
        defaultPubgitemShouldBeFound("opq.equals=" + DEFAULT_OPQ);

        // Get all the pubgitemList where opq equals to UPDATED_OPQ
        defaultPubgitemShouldNotBeFound("opq.equals=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOpqIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where opq in DEFAULT_OPQ or UPDATED_OPQ
        defaultPubgitemShouldBeFound("opq.in=" + DEFAULT_OPQ + "," + UPDATED_OPQ);

        // Get all the pubgitemList where opq equals to UPDATED_OPQ
        defaultPubgitemShouldNotBeFound("opq.in=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOpqIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where opq is not null
        defaultPubgitemShouldBeFound("opq.specified=true");

        // Get all the pubgitemList where opq is null
        defaultPubgitemShouldNotBeFound("opq.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOpqIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where opq greater than or equals to DEFAULT_OPQ
        defaultPubgitemShouldBeFound("opq.greaterOrEqualThan=" + DEFAULT_OPQ);

        // Get all the pubgitemList where opq greater than or equals to UPDATED_OPQ
        defaultPubgitemShouldNotBeFound("opq.greaterOrEqualThan=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByOpqIsLessThanSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where opq less than or equals to DEFAULT_OPQ
        defaultPubgitemShouldNotBeFound("opq.lessThan=" + DEFAULT_OPQ);

        // Get all the pubgitemList where opq less than or equals to UPDATED_OPQ
        defaultPubgitemShouldBeFound("opq.lessThan=" + UPDATED_OPQ);
    }


    @Test
    @Transactional
    public void getAllPubgitemsByNidIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where nid equals to DEFAULT_NID
        defaultPubgitemShouldBeFound("nid.equals=" + DEFAULT_NID);

        // Get all the pubgitemList where nid equals to UPDATED_NID
        defaultPubgitemShouldNotBeFound("nid.equals=" + UPDATED_NID);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByNidIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where nid in DEFAULT_NID or UPDATED_NID
        defaultPubgitemShouldBeFound("nid.in=" + DEFAULT_NID + "," + UPDATED_NID);

        // Get all the pubgitemList where nid equals to UPDATED_NID
        defaultPubgitemShouldNotBeFound("nid.in=" + UPDATED_NID);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByNidIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where nid is not null
        defaultPubgitemShouldBeFound("nid.specified=true");

        // Get all the pubgitemList where nid is null
        defaultPubgitemShouldNotBeFound("nid.specified=false");
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUatIsEqualToSomething() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uat equals to DEFAULT_UAT
        defaultPubgitemShouldBeFound("uat.equals=" + DEFAULT_UAT);

        // Get all the pubgitemList where uat equals to UPDATED_UAT
        defaultPubgitemShouldNotBeFound("uat.equals=" + UPDATED_UAT);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUatIsInShouldWork() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uat in DEFAULT_UAT or UPDATED_UAT
        defaultPubgitemShouldBeFound("uat.in=" + DEFAULT_UAT + "," + UPDATED_UAT);

        // Get all the pubgitemList where uat equals to UPDATED_UAT
        defaultPubgitemShouldNotBeFound("uat.in=" + UPDATED_UAT);
    }

    @Test
    @Transactional
    public void getAllPubgitemsByUatIsNullOrNotNull() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);

        // Get all the pubgitemList where uat is not null
        defaultPubgitemShouldBeFound("uat.specified=true");

        // Get all the pubgitemList where uat is null
        defaultPubgitemShouldNotBeFound("uat.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPubgitemShouldBeFound(String filter) throws Exception {
        restPubgitemMockMvc.perform(get("/api/pubgitems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pubgitem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].uns").value(hasItem(DEFAULT_UNS.booleanValue())))
            .andExpect(jsonPath("$.[*].unsr").value(hasItem(DEFAULT_UNSR.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.doubleValue())))
            .andExpect(jsonPath("$.[*].maxp").value(hasItem(DEFAULT_MAXP.doubleValue())))
            .andExpect(jsonPath("$.[*].avgp").value(hasItem(DEFAULT_AVGP.doubleValue())))
            .andExpect(jsonPath("$.[*].minp").value(hasItem(DEFAULT_MINP.doubleValue())))
            .andExpect(jsonPath("$.[*].savgd").value(hasItem(DEFAULT_SAVGD)))
            .andExpect(jsonPath("$.[*].s24h").value(hasItem(DEFAULT_S_24_H)))
            .andExpect(jsonPath("$.[*].s7d").value(hasItem(DEFAULT_S_7_D)))
            .andExpect(jsonPath("$.[*].s30d").value(hasItem(DEFAULT_S_30_D)))
            .andExpect(jsonPath("$.[*].s90d").value(hasItem(DEFAULT_S_90_D)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].dopx").value(hasItem(DEFAULT_DOPX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].nid").value(hasItem(DEFAULT_NID.toString())))
            .andExpect(jsonPath("$.[*].uat").value(hasItem(DEFAULT_UAT.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPubgitemShouldNotBeFound(String filter) throws Exception {
        restPubgitemMockMvc.perform(get("/api/pubgitems?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingPubgitem() throws Exception {
        // Get the pubgitem
        restPubgitemMockMvc.perform(get("/api/pubgitems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePubgitem() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);
        pubgitemSearchRepository.save(pubgitem);
        int databaseSizeBeforeUpdate = pubgitemRepository.findAll().size();

        // Update the pubgitem
        Pubgitem updatedPubgitem = pubgitemRepository.findOne(pubgitem.getId());
        updatedPubgitem
            .name(UPDATED_NAME)
            .uns(UPDATED_UNS)
            .unsr(UPDATED_UNSR)
            .rank(UPDATED_RANK)
            .sp(UPDATED_SP)
            .maxp(UPDATED_MAXP)
            .avgp(UPDATED_AVGP)
            .minp(UPDATED_MINP)
            .savgd(UPDATED_SAVGD)
            .s24h(UPDATED_S_24_H)
            .s7d(UPDATED_S_7_D)
            .s30d(UPDATED_S_30_D)
            .s90d(UPDATED_S_90_D)
            .cfp(UPDATED_CFP)
            .iop(UPDATED_IOP)
            .dcx(UPDATED_DCX)
            .dopx(UPDATED_DOPX)
            .oplp(UPDATED_OPLP)
            .opq(UPDATED_OPQ)
            .nid(UPDATED_NID)
            .uat(UPDATED_UAT);
        PubgitemDTO pubgitemDTO = pubgitemMapper.toDto(updatedPubgitem);

        restPubgitemMockMvc.perform(put("/api/pubgitems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pubgitemDTO)))
            .andExpect(status().isOk());

        // Validate the Pubgitem in the database
        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeUpdate);
        Pubgitem testPubgitem = pubgitemList.get(pubgitemList.size() - 1);
        assertThat(testPubgitem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPubgitem.isUns()).isEqualTo(UPDATED_UNS);
        assertThat(testPubgitem.getUnsr()).isEqualTo(UPDATED_UNSR);
        assertThat(testPubgitem.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testPubgitem.getSp()).isEqualTo(UPDATED_SP);
        assertThat(testPubgitem.getMaxp()).isEqualTo(UPDATED_MAXP);
        assertThat(testPubgitem.getAvgp()).isEqualTo(UPDATED_AVGP);
        assertThat(testPubgitem.getMinp()).isEqualTo(UPDATED_MINP);
        assertThat(testPubgitem.getSavgd()).isEqualTo(UPDATED_SAVGD);
        assertThat(testPubgitem.gets24h()).isEqualTo(UPDATED_S_24_H);
        assertThat(testPubgitem.gets7d()).isEqualTo(UPDATED_S_7_D);
        assertThat(testPubgitem.gets30d()).isEqualTo(UPDATED_S_30_D);
        assertThat(testPubgitem.gets90d()).isEqualTo(UPDATED_S_90_D);
        assertThat(testPubgitem.getCfp()).isEqualTo(UPDATED_CFP);
        assertThat(testPubgitem.getIop()).isEqualTo(UPDATED_IOP);
        assertThat(testPubgitem.getDcx()).isEqualTo(UPDATED_DCX);
        assertThat(testPubgitem.getDopx()).isEqualTo(UPDATED_DOPX);
        assertThat(testPubgitem.getOplp()).isEqualTo(UPDATED_OPLP);
        assertThat(testPubgitem.getOpq()).isEqualTo(UPDATED_OPQ);
        assertThat(testPubgitem.getNid()).isEqualTo(UPDATED_NID);
        assertThat(testPubgitem.getUat()).isEqualTo(UPDATED_UAT);

        // Validate the Pubgitem in Elasticsearch
        Pubgitem pubgitemEs = pubgitemSearchRepository.findOne(testPubgitem.getId());
        assertThat(pubgitemEs).isEqualToComparingFieldByField(testPubgitem);
    }

    @Test
    @Transactional
    public void updateNonExistingPubgitem() throws Exception {
        int databaseSizeBeforeUpdate = pubgitemRepository.findAll().size();

        // Create the Pubgitem
        PubgitemDTO pubgitemDTO = pubgitemMapper.toDto(pubgitem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPubgitemMockMvc.perform(put("/api/pubgitems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pubgitemDTO)))
            .andExpect(status().isCreated());

        // Validate the Pubgitem in the database
        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePubgitem() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);
        pubgitemSearchRepository.save(pubgitem);
        int databaseSizeBeforeDelete = pubgitemRepository.findAll().size();

        // Get the pubgitem
        restPubgitemMockMvc.perform(delete("/api/pubgitems/{id}", pubgitem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean pubgitemExistsInEs = pubgitemSearchRepository.exists(pubgitem.getId());
        assertThat(pubgitemExistsInEs).isFalse();

        // Validate the database is empty
        List<Pubgitem> pubgitemList = pubgitemRepository.findAll();
        assertThat(pubgitemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPubgitem() throws Exception {
        // Initialize the database
        pubgitemRepository.saveAndFlush(pubgitem);
        pubgitemSearchRepository.save(pubgitem);

        // Search the pubgitem
        restPubgitemMockMvc.perform(get("/api/_search/pubgitems?query=id:" + pubgitem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pubgitem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].uns").value(hasItem(DEFAULT_UNS.booleanValue())))
            .andExpect(jsonPath("$.[*].unsr").value(hasItem(DEFAULT_UNSR.toString())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.doubleValue())))
            .andExpect(jsonPath("$.[*].maxp").value(hasItem(DEFAULT_MAXP.doubleValue())))
            .andExpect(jsonPath("$.[*].avgp").value(hasItem(DEFAULT_AVGP.doubleValue())))
            .andExpect(jsonPath("$.[*].minp").value(hasItem(DEFAULT_MINP.doubleValue())))
            .andExpect(jsonPath("$.[*].savgd").value(hasItem(DEFAULT_SAVGD)))
            .andExpect(jsonPath("$.[*].s24h").value(hasItem(DEFAULT_S_24_H)))
            .andExpect(jsonPath("$.[*].s7d").value(hasItem(DEFAULT_S_7_D)))
            .andExpect(jsonPath("$.[*].s30d").value(hasItem(DEFAULT_S_30_D)))
            .andExpect(jsonPath("$.[*].s90d").value(hasItem(DEFAULT_S_90_D)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].dopx").value(hasItem(DEFAULT_DOPX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].nid").value(hasItem(DEFAULT_NID.toString())))
            .andExpect(jsonPath("$.[*].uat").value(hasItem(DEFAULT_UAT.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pubgitem.class);
        Pubgitem pubgitem1 = new Pubgitem();
        pubgitem1.setId(1L);
        Pubgitem pubgitem2 = new Pubgitem();
        pubgitem2.setId(pubgitem1.getId());
        assertThat(pubgitem1).isEqualTo(pubgitem2);
        pubgitem2.setId(2L);
        assertThat(pubgitem1).isNotEqualTo(pubgitem2);
        pubgitem1.setId(null);
        assertThat(pubgitem1).isNotEqualTo(pubgitem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PubgitemDTO.class);
        PubgitemDTO pubgitemDTO1 = new PubgitemDTO();
        pubgitemDTO1.setId(1L);
        PubgitemDTO pubgitemDTO2 = new PubgitemDTO();
        assertThat(pubgitemDTO1).isNotEqualTo(pubgitemDTO2);
        pubgitemDTO2.setId(pubgitemDTO1.getId());
        assertThat(pubgitemDTO1).isEqualTo(pubgitemDTO2);
        pubgitemDTO2.setId(2L);
        assertThat(pubgitemDTO1).isNotEqualTo(pubgitemDTO2);
        pubgitemDTO1.setId(null);
        assertThat(pubgitemDTO1).isNotEqualTo(pubgitemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pubgitemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pubgitemMapper.fromId(null)).isNull();
    }
}
