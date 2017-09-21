package com.mma.mma.web.rest;

import com.mma.mma.MmaApp;

import com.mma.mma.domain.CsgoItem;
import com.mma.mma.repository.CsgoItemRepository;
import com.mma.mma.service.CsgoItemService;
import com.mma.mma.repository.search.CsgoItemSearchRepository;
import com.mma.mma.service.dto.CsgoItemDTO;
import com.mma.mma.service.mapper.CsgoItemMapper;
import com.mma.mma.web.rest.errors.ExceptionTranslator;
import com.mma.mma.service.dto.CsgoItemCriteria;
import com.mma.mma.service.CsgoItemQueryService;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CsgoItemResource REST controller.
 *
 * @see CsgoItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MmaApp.class)
public class CsgoItemResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SP = new BigDecimal(1);
    private static final BigDecimal UPDATED_SP = new BigDecimal(2);

    private static final Boolean DEFAULT_OPM = false;
    private static final Boolean UPDATED_OPM = true;

    private static final Integer DEFAULT_VOL = 1;
    private static final Integer UPDATED_VOL = 2;

    private static final BigDecimal DEFAULT_MP_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MP_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVG_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVG_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LP_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_LP_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HP_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_HP_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAD_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAD_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DP_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_DP_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TREND_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TREND_7 = new BigDecimal(2);

    private static final Integer DEFAULT_VOL_7 = 1;
    private static final Integer UPDATED_VOL_7 = 2;

    private static final BigDecimal DEFAULT_MP_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MP_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVG_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVG_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LP_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_LP_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HP_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_HP_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAD_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAD_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DP_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_DP_30 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TREND_30 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TREND_30 = new BigDecimal(2);

    private static final Integer DEFAULT_VOL_30 = 1;
    private static final Integer UPDATED_VOL_30 = 2;

    private static final BigDecimal DEFAULT_MP_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MP_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVG_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVG_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LP_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_LP_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HP_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_HP_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAD_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAD_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DP_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_DP_ALL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TREND_ALL = new BigDecimal(1);
    private static final BigDecimal UPDATED_TREND_ALL = new BigDecimal(2);

    private static final Integer DEFAULT_VOL_ALL = 1;
    private static final Integer UPDATED_VOL_ALL = 2;

    private static final Double DEFAULT_CFP = 1D;
    private static final Double UPDATED_CFP = 2D;

    private static final Double DEFAULT_IOP = 1D;
    private static final Double UPDATED_IOP = 2D;

    private static final Double DEFAULT_DCX = 1D;
    private static final Double UPDATED_DCX = 2D;

    private static final Double DEFAULT_OPLP = 1D;
    private static final Double UPDATED_OPLP = 2D;

    private static final Integer DEFAULT_OPQ = 1;
    private static final Integer UPDATED_OPQ = 2;

    private static final Instant DEFAULT_ADDED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CsgoItemRepository csgoItemRepository;

    @Autowired
    private CsgoItemMapper csgoItemMapper;

    @Autowired
    private CsgoItemService csgoItemService;

    @Autowired
    private CsgoItemSearchRepository csgoItemSearchRepository;

    @Autowired
    private CsgoItemQueryService csgoItemQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCsgoItemMockMvc;

    private CsgoItem csgoItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CsgoItemResource csgoItemResource = new CsgoItemResource(csgoItemService, csgoItemQueryService);
        this.restCsgoItemMockMvc = MockMvcBuilders.standaloneSetup(csgoItemResource)
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
    public static CsgoItem createEntity(EntityManager em) {
        CsgoItem csgoItem = new CsgoItem()
            .name(DEFAULT_NAME)
            .sp(DEFAULT_SP)
            .opm(DEFAULT_OPM)
            .vol(DEFAULT_VOL)
            .mp7(DEFAULT_MP_7)
            .avg7(DEFAULT_AVG_7)
            .lp7(DEFAULT_LP_7)
            .hp7(DEFAULT_HP_7)
            .mad7(DEFAULT_MAD_7)
            .dp7(DEFAULT_DP_7)
            .trend7(DEFAULT_TREND_7)
            .vol7(DEFAULT_VOL_7)
            .mp30(DEFAULT_MP_30)
            .avg30(DEFAULT_AVG_30)
            .lp30(DEFAULT_LP_30)
            .hp30(DEFAULT_HP_30)
            .mad30(DEFAULT_MAD_30)
            .dp30(DEFAULT_DP_30)
            .trend30(DEFAULT_TREND_30)
            .vol30(DEFAULT_VOL_30)
            .mpAll(DEFAULT_MP_ALL)
            .avgAll(DEFAULT_AVG_ALL)
            .lpAll(DEFAULT_LP_ALL)
            .hpAll(DEFAULT_HP_ALL)
            .madAll(DEFAULT_MAD_ALL)
            .dpAll(DEFAULT_DP_ALL)
            .trendAll(DEFAULT_TREND_ALL)
            .volAll(DEFAULT_VOL_ALL)
            .cfp(DEFAULT_CFP)
            .iop(DEFAULT_IOP)
            .dcx(DEFAULT_DCX)
            .oplp(DEFAULT_OPLP)
            .opq(DEFAULT_OPQ)
            .added(DEFAULT_ADDED);
        return csgoItem;
    }

    @Before
    public void initTest() {
        csgoItemSearchRepository.deleteAll();
        csgoItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsgoItem() throws Exception {
        int databaseSizeBeforeCreate = csgoItemRepository.findAll().size();

        // Create the CsgoItem
        CsgoItemDTO csgoItemDTO = csgoItemMapper.toDto(csgoItem);
        restCsgoItemMockMvc.perform(post("/api/csgo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(csgoItemDTO)))
            .andExpect(status().isCreated());

        // Validate the CsgoItem in the database
        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeCreate + 1);
        CsgoItem testCsgoItem = csgoItemList.get(csgoItemList.size() - 1);
        assertThat(testCsgoItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCsgoItem.getSp()).isEqualTo(DEFAULT_SP);
        assertThat(testCsgoItem.isOpm()).isEqualTo(DEFAULT_OPM);
        assertThat(testCsgoItem.getVol()).isEqualTo(DEFAULT_VOL);
        assertThat(testCsgoItem.getMp7()).isEqualTo(DEFAULT_MP_7);
        assertThat(testCsgoItem.getAvg7()).isEqualTo(DEFAULT_AVG_7);
        assertThat(testCsgoItem.getLp7()).isEqualTo(DEFAULT_LP_7);
        assertThat(testCsgoItem.getHp7()).isEqualTo(DEFAULT_HP_7);
        assertThat(testCsgoItem.getMad7()).isEqualTo(DEFAULT_MAD_7);
        assertThat(testCsgoItem.getDp7()).isEqualTo(DEFAULT_DP_7);
        assertThat(testCsgoItem.getTrend7()).isEqualTo(DEFAULT_TREND_7);
        assertThat(testCsgoItem.getVol7()).isEqualTo(DEFAULT_VOL_7);
        assertThat(testCsgoItem.getMp30()).isEqualTo(DEFAULT_MP_30);
        assertThat(testCsgoItem.getAvg30()).isEqualTo(DEFAULT_AVG_30);
        assertThat(testCsgoItem.getLp30()).isEqualTo(DEFAULT_LP_30);
        assertThat(testCsgoItem.getHp30()).isEqualTo(DEFAULT_HP_30);
        assertThat(testCsgoItem.getMad30()).isEqualTo(DEFAULT_MAD_30);
        assertThat(testCsgoItem.getDp30()).isEqualTo(DEFAULT_DP_30);
        assertThat(testCsgoItem.getTrend30()).isEqualTo(DEFAULT_TREND_30);
        assertThat(testCsgoItem.getVol30()).isEqualTo(DEFAULT_VOL_30);
        assertThat(testCsgoItem.getMpAll()).isEqualTo(DEFAULT_MP_ALL);
        assertThat(testCsgoItem.getAvgAll()).isEqualTo(DEFAULT_AVG_ALL);
        assertThat(testCsgoItem.getLpAll()).isEqualTo(DEFAULT_LP_ALL);
        assertThat(testCsgoItem.getHpAll()).isEqualTo(DEFAULT_HP_ALL);
        assertThat(testCsgoItem.getMadAll()).isEqualTo(DEFAULT_MAD_ALL);
        assertThat(testCsgoItem.getDpAll()).isEqualTo(DEFAULT_DP_ALL);
        assertThat(testCsgoItem.getTrendAll()).isEqualTo(DEFAULT_TREND_ALL);
        assertThat(testCsgoItem.getVolAll()).isEqualTo(DEFAULT_VOL_ALL);
        assertThat(testCsgoItem.getCfp()).isEqualTo(DEFAULT_CFP);
        assertThat(testCsgoItem.getIop()).isEqualTo(DEFAULT_IOP);
        assertThat(testCsgoItem.getDcx()).isEqualTo(DEFAULT_DCX);
        assertThat(testCsgoItem.getOplp()).isEqualTo(DEFAULT_OPLP);
        assertThat(testCsgoItem.getOpq()).isEqualTo(DEFAULT_OPQ);
        assertThat(testCsgoItem.getAdded()).isEqualTo(DEFAULT_ADDED);

        // Validate the CsgoItem in Elasticsearch
        CsgoItem csgoItemEs = csgoItemSearchRepository.findOne(testCsgoItem.getId());
        assertThat(csgoItemEs).isEqualToComparingFieldByField(testCsgoItem);
    }

    @Test
    @Transactional
    public void createCsgoItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csgoItemRepository.findAll().size();

        // Create the CsgoItem with an existing ID
        csgoItem.setId(1L);
        CsgoItemDTO csgoItemDTO = csgoItemMapper.toDto(csgoItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsgoItemMockMvc.perform(post("/api/csgo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(csgoItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsgoItem in the database
        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = csgoItemRepository.findAll().size();
        // set the field null
        csgoItem.setName(null);

        // Create the CsgoItem, which fails.
        CsgoItemDTO csgoItemDTO = csgoItemMapper.toDto(csgoItem);

        restCsgoItemMockMvc.perform(post("/api/csgo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(csgoItemDTO)))
            .andExpect(status().isBadRequest());

        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsgoItems() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList
        restCsgoItemMockMvc.perform(get("/api/csgo-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csgoItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.intValue())))
            .andExpect(jsonPath("$.[*].opm").value(hasItem(DEFAULT_OPM.booleanValue())))
            .andExpect(jsonPath("$.[*].vol").value(hasItem(DEFAULT_VOL)))
            .andExpect(jsonPath("$.[*].mp7").value(hasItem(DEFAULT_MP_7.intValue())))
            .andExpect(jsonPath("$.[*].avg7").value(hasItem(DEFAULT_AVG_7.intValue())))
            .andExpect(jsonPath("$.[*].lp7").value(hasItem(DEFAULT_LP_7.intValue())))
            .andExpect(jsonPath("$.[*].hp7").value(hasItem(DEFAULT_HP_7.intValue())))
            .andExpect(jsonPath("$.[*].mad7").value(hasItem(DEFAULT_MAD_7.intValue())))
            .andExpect(jsonPath("$.[*].dp7").value(hasItem(DEFAULT_DP_7.intValue())))
            .andExpect(jsonPath("$.[*].trend7").value(hasItem(DEFAULT_TREND_7.intValue())))
            .andExpect(jsonPath("$.[*].vol7").value(hasItem(DEFAULT_VOL_7)))
            .andExpect(jsonPath("$.[*].mp30").value(hasItem(DEFAULT_MP_30.intValue())))
            .andExpect(jsonPath("$.[*].avg30").value(hasItem(DEFAULT_AVG_30.intValue())))
            .andExpect(jsonPath("$.[*].lp30").value(hasItem(DEFAULT_LP_30.intValue())))
            .andExpect(jsonPath("$.[*].hp30").value(hasItem(DEFAULT_HP_30.intValue())))
            .andExpect(jsonPath("$.[*].mad30").value(hasItem(DEFAULT_MAD_30.intValue())))
            .andExpect(jsonPath("$.[*].dp30").value(hasItem(DEFAULT_DP_30.intValue())))
            .andExpect(jsonPath("$.[*].trend30").value(hasItem(DEFAULT_TREND_30.intValue())))
            .andExpect(jsonPath("$.[*].vol30").value(hasItem(DEFAULT_VOL_30)))
            .andExpect(jsonPath("$.[*].mpAll").value(hasItem(DEFAULT_MP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].avgAll").value(hasItem(DEFAULT_AVG_ALL.intValue())))
            .andExpect(jsonPath("$.[*].lpAll").value(hasItem(DEFAULT_LP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].hpAll").value(hasItem(DEFAULT_HP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].madAll").value(hasItem(DEFAULT_MAD_ALL.intValue())))
            .andExpect(jsonPath("$.[*].dpAll").value(hasItem(DEFAULT_DP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].trendAll").value(hasItem(DEFAULT_TREND_ALL.intValue())))
            .andExpect(jsonPath("$.[*].volAll").value(hasItem(DEFAULT_VOL_ALL)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].added").value(hasItem(DEFAULT_ADDED.toString())));
    }

    @Test
    @Transactional
    public void getCsgoItem() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get the csgoItem
        restCsgoItemMockMvc.perform(get("/api/csgo-items/{id}", csgoItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(csgoItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sp").value(DEFAULT_SP.intValue()))
            .andExpect(jsonPath("$.opm").value(DEFAULT_OPM.booleanValue()))
            .andExpect(jsonPath("$.vol").value(DEFAULT_VOL))
            .andExpect(jsonPath("$.mp7").value(DEFAULT_MP_7.intValue()))
            .andExpect(jsonPath("$.avg7").value(DEFAULT_AVG_7.intValue()))
            .andExpect(jsonPath("$.lp7").value(DEFAULT_LP_7.intValue()))
            .andExpect(jsonPath("$.hp7").value(DEFAULT_HP_7.intValue()))
            .andExpect(jsonPath("$.mad7").value(DEFAULT_MAD_7.intValue()))
            .andExpect(jsonPath("$.dp7").value(DEFAULT_DP_7.intValue()))
            .andExpect(jsonPath("$.trend7").value(DEFAULT_TREND_7.intValue()))
            .andExpect(jsonPath("$.vol7").value(DEFAULT_VOL_7))
            .andExpect(jsonPath("$.mp30").value(DEFAULT_MP_30.intValue()))
            .andExpect(jsonPath("$.avg30").value(DEFAULT_AVG_30.intValue()))
            .andExpect(jsonPath("$.lp30").value(DEFAULT_LP_30.intValue()))
            .andExpect(jsonPath("$.hp30").value(DEFAULT_HP_30.intValue()))
            .andExpect(jsonPath("$.mad30").value(DEFAULT_MAD_30.intValue()))
            .andExpect(jsonPath("$.dp30").value(DEFAULT_DP_30.intValue()))
            .andExpect(jsonPath("$.trend30").value(DEFAULT_TREND_30.intValue()))
            .andExpect(jsonPath("$.vol30").value(DEFAULT_VOL_30))
            .andExpect(jsonPath("$.mpAll").value(DEFAULT_MP_ALL.intValue()))
            .andExpect(jsonPath("$.avgAll").value(DEFAULT_AVG_ALL.intValue()))
            .andExpect(jsonPath("$.lpAll").value(DEFAULT_LP_ALL.intValue()))
            .andExpect(jsonPath("$.hpAll").value(DEFAULT_HP_ALL.intValue()))
            .andExpect(jsonPath("$.madAll").value(DEFAULT_MAD_ALL.intValue()))
            .andExpect(jsonPath("$.dpAll").value(DEFAULT_DP_ALL.intValue()))
            .andExpect(jsonPath("$.trendAll").value(DEFAULT_TREND_ALL.intValue()))
            .andExpect(jsonPath("$.volAll").value(DEFAULT_VOL_ALL))
            .andExpect(jsonPath("$.cfp").value(DEFAULT_CFP.doubleValue()))
            .andExpect(jsonPath("$.iop").value(DEFAULT_IOP.doubleValue()))
            .andExpect(jsonPath("$.dcx").value(DEFAULT_DCX.doubleValue()))
            .andExpect(jsonPath("$.oplp").value(DEFAULT_OPLP.doubleValue()))
            .andExpect(jsonPath("$.opq").value(DEFAULT_OPQ))
            .andExpect(jsonPath("$.added").value(DEFAULT_ADDED.toString()));
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where name equals to DEFAULT_NAME
        defaultCsgoItemShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the csgoItemList where name equals to UPDATED_NAME
        defaultCsgoItemShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where name in DEFAULT_NAME or UPDATED_NAME
        defaultCsgoItemShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the csgoItemList where name equals to UPDATED_NAME
        defaultCsgoItemShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where name is not null
        defaultCsgoItemShouldBeFound("name.specified=true");

        // Get all the csgoItemList where name is null
        defaultCsgoItemShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsBySpIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where sp equals to DEFAULT_SP
        defaultCsgoItemShouldBeFound("sp.equals=" + DEFAULT_SP);

        // Get all the csgoItemList where sp equals to UPDATED_SP
        defaultCsgoItemShouldNotBeFound("sp.equals=" + UPDATED_SP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsBySpIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where sp in DEFAULT_SP or UPDATED_SP
        defaultCsgoItemShouldBeFound("sp.in=" + DEFAULT_SP + "," + UPDATED_SP);

        // Get all the csgoItemList where sp equals to UPDATED_SP
        defaultCsgoItemShouldNotBeFound("sp.in=" + UPDATED_SP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsBySpIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where sp is not null
        defaultCsgoItemShouldBeFound("sp.specified=true");

        // Get all the csgoItemList where sp is null
        defaultCsgoItemShouldNotBeFound("sp.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpmIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opm equals to DEFAULT_OPM
        defaultCsgoItemShouldBeFound("opm.equals=" + DEFAULT_OPM);

        // Get all the csgoItemList where opm equals to UPDATED_OPM
        defaultCsgoItemShouldNotBeFound("opm.equals=" + UPDATED_OPM);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpmIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opm in DEFAULT_OPM or UPDATED_OPM
        defaultCsgoItemShouldBeFound("opm.in=" + DEFAULT_OPM + "," + UPDATED_OPM);

        // Get all the csgoItemList where opm equals to UPDATED_OPM
        defaultCsgoItemShouldNotBeFound("opm.in=" + UPDATED_OPM);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpmIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opm is not null
        defaultCsgoItemShouldBeFound("opm.specified=true");

        // Get all the csgoItemList where opm is null
        defaultCsgoItemShouldNotBeFound("opm.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol equals to DEFAULT_VOL
        defaultCsgoItemShouldBeFound("vol.equals=" + DEFAULT_VOL);

        // Get all the csgoItemList where vol equals to UPDATED_VOL
        defaultCsgoItemShouldNotBeFound("vol.equals=" + UPDATED_VOL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol in DEFAULT_VOL or UPDATED_VOL
        defaultCsgoItemShouldBeFound("vol.in=" + DEFAULT_VOL + "," + UPDATED_VOL);

        // Get all the csgoItemList where vol equals to UPDATED_VOL
        defaultCsgoItemShouldNotBeFound("vol.in=" + UPDATED_VOL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol is not null
        defaultCsgoItemShouldBeFound("vol.specified=true");

        // Get all the csgoItemList where vol is null
        defaultCsgoItemShouldNotBeFound("vol.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol greater than or equals to DEFAULT_VOL
        defaultCsgoItemShouldBeFound("vol.greaterOrEqualThan=" + DEFAULT_VOL);

        // Get all the csgoItemList where vol greater than or equals to UPDATED_VOL
        defaultCsgoItemShouldNotBeFound("vol.greaterOrEqualThan=" + UPDATED_VOL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolIsLessThanSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol less than or equals to DEFAULT_VOL
        defaultCsgoItemShouldNotBeFound("vol.lessThan=" + DEFAULT_VOL);

        // Get all the csgoItemList where vol less than or equals to UPDATED_VOL
        defaultCsgoItemShouldBeFound("vol.lessThan=" + UPDATED_VOL);
    }


    @Test
    @Transactional
    public void getAllCsgoItemsByMp7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp7 equals to DEFAULT_MP_7
        defaultCsgoItemShouldBeFound("mp7.equals=" + DEFAULT_MP_7);

        // Get all the csgoItemList where mp7 equals to UPDATED_MP_7
        defaultCsgoItemShouldNotBeFound("mp7.equals=" + UPDATED_MP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMp7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp7 in DEFAULT_MP_7 or UPDATED_MP_7
        defaultCsgoItemShouldBeFound("mp7.in=" + DEFAULT_MP_7 + "," + UPDATED_MP_7);

        // Get all the csgoItemList where mp7 equals to UPDATED_MP_7
        defaultCsgoItemShouldNotBeFound("mp7.in=" + UPDATED_MP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMp7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp7 is not null
        defaultCsgoItemShouldBeFound("mp7.specified=true");

        // Get all the csgoItemList where mp7 is null
        defaultCsgoItemShouldNotBeFound("mp7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg7 equals to DEFAULT_AVG_7
        defaultCsgoItemShouldBeFound("avg7.equals=" + DEFAULT_AVG_7);

        // Get all the csgoItemList where avg7 equals to UPDATED_AVG_7
        defaultCsgoItemShouldNotBeFound("avg7.equals=" + UPDATED_AVG_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg7 in DEFAULT_AVG_7 or UPDATED_AVG_7
        defaultCsgoItemShouldBeFound("avg7.in=" + DEFAULT_AVG_7 + "," + UPDATED_AVG_7);

        // Get all the csgoItemList where avg7 equals to UPDATED_AVG_7
        defaultCsgoItemShouldNotBeFound("avg7.in=" + UPDATED_AVG_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg7 is not null
        defaultCsgoItemShouldBeFound("avg7.specified=true");

        // Get all the csgoItemList where avg7 is null
        defaultCsgoItemShouldNotBeFound("avg7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp7 equals to DEFAULT_LP_7
        defaultCsgoItemShouldBeFound("lp7.equals=" + DEFAULT_LP_7);

        // Get all the csgoItemList where lp7 equals to UPDATED_LP_7
        defaultCsgoItemShouldNotBeFound("lp7.equals=" + UPDATED_LP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp7 in DEFAULT_LP_7 or UPDATED_LP_7
        defaultCsgoItemShouldBeFound("lp7.in=" + DEFAULT_LP_7 + "," + UPDATED_LP_7);

        // Get all the csgoItemList where lp7 equals to UPDATED_LP_7
        defaultCsgoItemShouldNotBeFound("lp7.in=" + UPDATED_LP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp7 is not null
        defaultCsgoItemShouldBeFound("lp7.specified=true");

        // Get all the csgoItemList where lp7 is null
        defaultCsgoItemShouldNotBeFound("lp7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp7 equals to DEFAULT_HP_7
        defaultCsgoItemShouldBeFound("hp7.equals=" + DEFAULT_HP_7);

        // Get all the csgoItemList where hp7 equals to UPDATED_HP_7
        defaultCsgoItemShouldNotBeFound("hp7.equals=" + UPDATED_HP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp7 in DEFAULT_HP_7 or UPDATED_HP_7
        defaultCsgoItemShouldBeFound("hp7.in=" + DEFAULT_HP_7 + "," + UPDATED_HP_7);

        // Get all the csgoItemList where hp7 equals to UPDATED_HP_7
        defaultCsgoItemShouldNotBeFound("hp7.in=" + UPDATED_HP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp7 is not null
        defaultCsgoItemShouldBeFound("hp7.specified=true");

        // Get all the csgoItemList where hp7 is null
        defaultCsgoItemShouldNotBeFound("hp7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad7 equals to DEFAULT_MAD_7
        defaultCsgoItemShouldBeFound("mad7.equals=" + DEFAULT_MAD_7);

        // Get all the csgoItemList where mad7 equals to UPDATED_MAD_7
        defaultCsgoItemShouldNotBeFound("mad7.equals=" + UPDATED_MAD_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad7 in DEFAULT_MAD_7 or UPDATED_MAD_7
        defaultCsgoItemShouldBeFound("mad7.in=" + DEFAULT_MAD_7 + "," + UPDATED_MAD_7);

        // Get all the csgoItemList where mad7 equals to UPDATED_MAD_7
        defaultCsgoItemShouldNotBeFound("mad7.in=" + UPDATED_MAD_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad7 is not null
        defaultCsgoItemShouldBeFound("mad7.specified=true");

        // Get all the csgoItemList where mad7 is null
        defaultCsgoItemShouldNotBeFound("mad7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp7 equals to DEFAULT_DP_7
        defaultCsgoItemShouldBeFound("dp7.equals=" + DEFAULT_DP_7);

        // Get all the csgoItemList where dp7 equals to UPDATED_DP_7
        defaultCsgoItemShouldNotBeFound("dp7.equals=" + UPDATED_DP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp7 in DEFAULT_DP_7 or UPDATED_DP_7
        defaultCsgoItemShouldBeFound("dp7.in=" + DEFAULT_DP_7 + "," + UPDATED_DP_7);

        // Get all the csgoItemList where dp7 equals to UPDATED_DP_7
        defaultCsgoItemShouldNotBeFound("dp7.in=" + UPDATED_DP_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp7 is not null
        defaultCsgoItemShouldBeFound("dp7.specified=true");

        // Get all the csgoItemList where dp7 is null
        defaultCsgoItemShouldNotBeFound("dp7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend7 equals to DEFAULT_TREND_7
        defaultCsgoItemShouldBeFound("trend7.equals=" + DEFAULT_TREND_7);

        // Get all the csgoItemList where trend7 equals to UPDATED_TREND_7
        defaultCsgoItemShouldNotBeFound("trend7.equals=" + UPDATED_TREND_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend7 in DEFAULT_TREND_7 or UPDATED_TREND_7
        defaultCsgoItemShouldBeFound("trend7.in=" + DEFAULT_TREND_7 + "," + UPDATED_TREND_7);

        // Get all the csgoItemList where trend7 equals to UPDATED_TREND_7
        defaultCsgoItemShouldNotBeFound("trend7.in=" + UPDATED_TREND_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend7 is not null
        defaultCsgoItemShouldBeFound("trend7.specified=true");

        // Get all the csgoItemList where trend7 is null
        defaultCsgoItemShouldNotBeFound("trend7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol7IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol7 equals to DEFAULT_VOL_7
        defaultCsgoItemShouldBeFound("vol7.equals=" + DEFAULT_VOL_7);

        // Get all the csgoItemList where vol7 equals to UPDATED_VOL_7
        defaultCsgoItemShouldNotBeFound("vol7.equals=" + UPDATED_VOL_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol7IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol7 in DEFAULT_VOL_7 or UPDATED_VOL_7
        defaultCsgoItemShouldBeFound("vol7.in=" + DEFAULT_VOL_7 + "," + UPDATED_VOL_7);

        // Get all the csgoItemList where vol7 equals to UPDATED_VOL_7
        defaultCsgoItemShouldNotBeFound("vol7.in=" + UPDATED_VOL_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol7IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol7 is not null
        defaultCsgoItemShouldBeFound("vol7.specified=true");

        // Get all the csgoItemList where vol7 is null
        defaultCsgoItemShouldNotBeFound("vol7.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol7 greater than or equals to DEFAULT_VOL_7
        defaultCsgoItemShouldBeFound("vol7.greaterOrEqualThan=" + DEFAULT_VOL_7);

        // Get all the csgoItemList where vol7 greater than or equals to UPDATED_VOL_7
        defaultCsgoItemShouldNotBeFound("vol7.greaterOrEqualThan=" + UPDATED_VOL_7);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol7IsLessThanSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol7 less than or equals to DEFAULT_VOL_7
        defaultCsgoItemShouldNotBeFound("vol7.lessThan=" + DEFAULT_VOL_7);

        // Get all the csgoItemList where vol7 less than or equals to UPDATED_VOL_7
        defaultCsgoItemShouldBeFound("vol7.lessThan=" + UPDATED_VOL_7);
    }


    @Test
    @Transactional
    public void getAllCsgoItemsByMp30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp30 equals to DEFAULT_MP_30
        defaultCsgoItemShouldBeFound("mp30.equals=" + DEFAULT_MP_30);

        // Get all the csgoItemList where mp30 equals to UPDATED_MP_30
        defaultCsgoItemShouldNotBeFound("mp30.equals=" + UPDATED_MP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMp30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp30 in DEFAULT_MP_30 or UPDATED_MP_30
        defaultCsgoItemShouldBeFound("mp30.in=" + DEFAULT_MP_30 + "," + UPDATED_MP_30);

        // Get all the csgoItemList where mp30 equals to UPDATED_MP_30
        defaultCsgoItemShouldNotBeFound("mp30.in=" + UPDATED_MP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMp30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mp30 is not null
        defaultCsgoItemShouldBeFound("mp30.specified=true");

        // Get all the csgoItemList where mp30 is null
        defaultCsgoItemShouldNotBeFound("mp30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg30 equals to DEFAULT_AVG_30
        defaultCsgoItemShouldBeFound("avg30.equals=" + DEFAULT_AVG_30);

        // Get all the csgoItemList where avg30 equals to UPDATED_AVG_30
        defaultCsgoItemShouldNotBeFound("avg30.equals=" + UPDATED_AVG_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg30 in DEFAULT_AVG_30 or UPDATED_AVG_30
        defaultCsgoItemShouldBeFound("avg30.in=" + DEFAULT_AVG_30 + "," + UPDATED_AVG_30);

        // Get all the csgoItemList where avg30 equals to UPDATED_AVG_30
        defaultCsgoItemShouldNotBeFound("avg30.in=" + UPDATED_AVG_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvg30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avg30 is not null
        defaultCsgoItemShouldBeFound("avg30.specified=true");

        // Get all the csgoItemList where avg30 is null
        defaultCsgoItemShouldNotBeFound("avg30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp30 equals to DEFAULT_LP_30
        defaultCsgoItemShouldBeFound("lp30.equals=" + DEFAULT_LP_30);

        // Get all the csgoItemList where lp30 equals to UPDATED_LP_30
        defaultCsgoItemShouldNotBeFound("lp30.equals=" + UPDATED_LP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp30 in DEFAULT_LP_30 or UPDATED_LP_30
        defaultCsgoItemShouldBeFound("lp30.in=" + DEFAULT_LP_30 + "," + UPDATED_LP_30);

        // Get all the csgoItemList where lp30 equals to UPDATED_LP_30
        defaultCsgoItemShouldNotBeFound("lp30.in=" + UPDATED_LP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLp30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lp30 is not null
        defaultCsgoItemShouldBeFound("lp30.specified=true");

        // Get all the csgoItemList where lp30 is null
        defaultCsgoItemShouldNotBeFound("lp30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp30 equals to DEFAULT_HP_30
        defaultCsgoItemShouldBeFound("hp30.equals=" + DEFAULT_HP_30);

        // Get all the csgoItemList where hp30 equals to UPDATED_HP_30
        defaultCsgoItemShouldNotBeFound("hp30.equals=" + UPDATED_HP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp30 in DEFAULT_HP_30 or UPDATED_HP_30
        defaultCsgoItemShouldBeFound("hp30.in=" + DEFAULT_HP_30 + "," + UPDATED_HP_30);

        // Get all the csgoItemList where hp30 equals to UPDATED_HP_30
        defaultCsgoItemShouldNotBeFound("hp30.in=" + UPDATED_HP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHp30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hp30 is not null
        defaultCsgoItemShouldBeFound("hp30.specified=true");

        // Get all the csgoItemList where hp30 is null
        defaultCsgoItemShouldNotBeFound("hp30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad30 equals to DEFAULT_MAD_30
        defaultCsgoItemShouldBeFound("mad30.equals=" + DEFAULT_MAD_30);

        // Get all the csgoItemList where mad30 equals to UPDATED_MAD_30
        defaultCsgoItemShouldNotBeFound("mad30.equals=" + UPDATED_MAD_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad30 in DEFAULT_MAD_30 or UPDATED_MAD_30
        defaultCsgoItemShouldBeFound("mad30.in=" + DEFAULT_MAD_30 + "," + UPDATED_MAD_30);

        // Get all the csgoItemList where mad30 equals to UPDATED_MAD_30
        defaultCsgoItemShouldNotBeFound("mad30.in=" + UPDATED_MAD_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMad30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mad30 is not null
        defaultCsgoItemShouldBeFound("mad30.specified=true");

        // Get all the csgoItemList where mad30 is null
        defaultCsgoItemShouldNotBeFound("mad30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp30 equals to DEFAULT_DP_30
        defaultCsgoItemShouldBeFound("dp30.equals=" + DEFAULT_DP_30);

        // Get all the csgoItemList where dp30 equals to UPDATED_DP_30
        defaultCsgoItemShouldNotBeFound("dp30.equals=" + UPDATED_DP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp30 in DEFAULT_DP_30 or UPDATED_DP_30
        defaultCsgoItemShouldBeFound("dp30.in=" + DEFAULT_DP_30 + "," + UPDATED_DP_30);

        // Get all the csgoItemList where dp30 equals to UPDATED_DP_30
        defaultCsgoItemShouldNotBeFound("dp30.in=" + UPDATED_DP_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDp30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dp30 is not null
        defaultCsgoItemShouldBeFound("dp30.specified=true");

        // Get all the csgoItemList where dp30 is null
        defaultCsgoItemShouldNotBeFound("dp30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend30 equals to DEFAULT_TREND_30
        defaultCsgoItemShouldBeFound("trend30.equals=" + DEFAULT_TREND_30);

        // Get all the csgoItemList where trend30 equals to UPDATED_TREND_30
        defaultCsgoItemShouldNotBeFound("trend30.equals=" + UPDATED_TREND_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend30 in DEFAULT_TREND_30 or UPDATED_TREND_30
        defaultCsgoItemShouldBeFound("trend30.in=" + DEFAULT_TREND_30 + "," + UPDATED_TREND_30);

        // Get all the csgoItemList where trend30 equals to UPDATED_TREND_30
        defaultCsgoItemShouldNotBeFound("trend30.in=" + UPDATED_TREND_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrend30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trend30 is not null
        defaultCsgoItemShouldBeFound("trend30.specified=true");

        // Get all the csgoItemList where trend30 is null
        defaultCsgoItemShouldNotBeFound("trend30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol30IsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol30 equals to DEFAULT_VOL_30
        defaultCsgoItemShouldBeFound("vol30.equals=" + DEFAULT_VOL_30);

        // Get all the csgoItemList where vol30 equals to UPDATED_VOL_30
        defaultCsgoItemShouldNotBeFound("vol30.equals=" + UPDATED_VOL_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol30IsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol30 in DEFAULT_VOL_30 or UPDATED_VOL_30
        defaultCsgoItemShouldBeFound("vol30.in=" + DEFAULT_VOL_30 + "," + UPDATED_VOL_30);

        // Get all the csgoItemList where vol30 equals to UPDATED_VOL_30
        defaultCsgoItemShouldNotBeFound("vol30.in=" + UPDATED_VOL_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol30IsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol30 is not null
        defaultCsgoItemShouldBeFound("vol30.specified=true");

        // Get all the csgoItemList where vol30 is null
        defaultCsgoItemShouldNotBeFound("vol30.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol30IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol30 greater than or equals to DEFAULT_VOL_30
        defaultCsgoItemShouldBeFound("vol30.greaterOrEqualThan=" + DEFAULT_VOL_30);

        // Get all the csgoItemList where vol30 greater than or equals to UPDATED_VOL_30
        defaultCsgoItemShouldNotBeFound("vol30.greaterOrEqualThan=" + UPDATED_VOL_30);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVol30IsLessThanSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where vol30 less than or equals to DEFAULT_VOL_30
        defaultCsgoItemShouldNotBeFound("vol30.lessThan=" + DEFAULT_VOL_30);

        // Get all the csgoItemList where vol30 less than or equals to UPDATED_VOL_30
        defaultCsgoItemShouldBeFound("vol30.lessThan=" + UPDATED_VOL_30);
    }


    @Test
    @Transactional
    public void getAllCsgoItemsByMpAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mpAll equals to DEFAULT_MP_ALL
        defaultCsgoItemShouldBeFound("mpAll.equals=" + DEFAULT_MP_ALL);

        // Get all the csgoItemList where mpAll equals to UPDATED_MP_ALL
        defaultCsgoItemShouldNotBeFound("mpAll.equals=" + UPDATED_MP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMpAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mpAll in DEFAULT_MP_ALL or UPDATED_MP_ALL
        defaultCsgoItemShouldBeFound("mpAll.in=" + DEFAULT_MP_ALL + "," + UPDATED_MP_ALL);

        // Get all the csgoItemList where mpAll equals to UPDATED_MP_ALL
        defaultCsgoItemShouldNotBeFound("mpAll.in=" + UPDATED_MP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMpAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where mpAll is not null
        defaultCsgoItemShouldBeFound("mpAll.specified=true");

        // Get all the csgoItemList where mpAll is null
        defaultCsgoItemShouldNotBeFound("mpAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvgAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avgAll equals to DEFAULT_AVG_ALL
        defaultCsgoItemShouldBeFound("avgAll.equals=" + DEFAULT_AVG_ALL);

        // Get all the csgoItemList where avgAll equals to UPDATED_AVG_ALL
        defaultCsgoItemShouldNotBeFound("avgAll.equals=" + UPDATED_AVG_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvgAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avgAll in DEFAULT_AVG_ALL or UPDATED_AVG_ALL
        defaultCsgoItemShouldBeFound("avgAll.in=" + DEFAULT_AVG_ALL + "," + UPDATED_AVG_ALL);

        // Get all the csgoItemList where avgAll equals to UPDATED_AVG_ALL
        defaultCsgoItemShouldNotBeFound("avgAll.in=" + UPDATED_AVG_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAvgAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where avgAll is not null
        defaultCsgoItemShouldBeFound("avgAll.specified=true");

        // Get all the csgoItemList where avgAll is null
        defaultCsgoItemShouldNotBeFound("avgAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLpAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lpAll equals to DEFAULT_LP_ALL
        defaultCsgoItemShouldBeFound("lpAll.equals=" + DEFAULT_LP_ALL);

        // Get all the csgoItemList where lpAll equals to UPDATED_LP_ALL
        defaultCsgoItemShouldNotBeFound("lpAll.equals=" + UPDATED_LP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLpAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lpAll in DEFAULT_LP_ALL or UPDATED_LP_ALL
        defaultCsgoItemShouldBeFound("lpAll.in=" + DEFAULT_LP_ALL + "," + UPDATED_LP_ALL);

        // Get all the csgoItemList where lpAll equals to UPDATED_LP_ALL
        defaultCsgoItemShouldNotBeFound("lpAll.in=" + UPDATED_LP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByLpAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where lpAll is not null
        defaultCsgoItemShouldBeFound("lpAll.specified=true");

        // Get all the csgoItemList where lpAll is null
        defaultCsgoItemShouldNotBeFound("lpAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHpAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hpAll equals to DEFAULT_HP_ALL
        defaultCsgoItemShouldBeFound("hpAll.equals=" + DEFAULT_HP_ALL);

        // Get all the csgoItemList where hpAll equals to UPDATED_HP_ALL
        defaultCsgoItemShouldNotBeFound("hpAll.equals=" + UPDATED_HP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHpAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hpAll in DEFAULT_HP_ALL or UPDATED_HP_ALL
        defaultCsgoItemShouldBeFound("hpAll.in=" + DEFAULT_HP_ALL + "," + UPDATED_HP_ALL);

        // Get all the csgoItemList where hpAll equals to UPDATED_HP_ALL
        defaultCsgoItemShouldNotBeFound("hpAll.in=" + UPDATED_HP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByHpAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where hpAll is not null
        defaultCsgoItemShouldBeFound("hpAll.specified=true");

        // Get all the csgoItemList where hpAll is null
        defaultCsgoItemShouldNotBeFound("hpAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMadAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where madAll equals to DEFAULT_MAD_ALL
        defaultCsgoItemShouldBeFound("madAll.equals=" + DEFAULT_MAD_ALL);

        // Get all the csgoItemList where madAll equals to UPDATED_MAD_ALL
        defaultCsgoItemShouldNotBeFound("madAll.equals=" + UPDATED_MAD_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMadAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where madAll in DEFAULT_MAD_ALL or UPDATED_MAD_ALL
        defaultCsgoItemShouldBeFound("madAll.in=" + DEFAULT_MAD_ALL + "," + UPDATED_MAD_ALL);

        // Get all the csgoItemList where madAll equals to UPDATED_MAD_ALL
        defaultCsgoItemShouldNotBeFound("madAll.in=" + UPDATED_MAD_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByMadAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where madAll is not null
        defaultCsgoItemShouldBeFound("madAll.specified=true");

        // Get all the csgoItemList where madAll is null
        defaultCsgoItemShouldNotBeFound("madAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDpAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dpAll equals to DEFAULT_DP_ALL
        defaultCsgoItemShouldBeFound("dpAll.equals=" + DEFAULT_DP_ALL);

        // Get all the csgoItemList where dpAll equals to UPDATED_DP_ALL
        defaultCsgoItemShouldNotBeFound("dpAll.equals=" + UPDATED_DP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDpAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dpAll in DEFAULT_DP_ALL or UPDATED_DP_ALL
        defaultCsgoItemShouldBeFound("dpAll.in=" + DEFAULT_DP_ALL + "," + UPDATED_DP_ALL);

        // Get all the csgoItemList where dpAll equals to UPDATED_DP_ALL
        defaultCsgoItemShouldNotBeFound("dpAll.in=" + UPDATED_DP_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDpAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dpAll is not null
        defaultCsgoItemShouldBeFound("dpAll.specified=true");

        // Get all the csgoItemList where dpAll is null
        defaultCsgoItemShouldNotBeFound("dpAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrendAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trendAll equals to DEFAULT_TREND_ALL
        defaultCsgoItemShouldBeFound("trendAll.equals=" + DEFAULT_TREND_ALL);

        // Get all the csgoItemList where trendAll equals to UPDATED_TREND_ALL
        defaultCsgoItemShouldNotBeFound("trendAll.equals=" + UPDATED_TREND_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrendAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trendAll in DEFAULT_TREND_ALL or UPDATED_TREND_ALL
        defaultCsgoItemShouldBeFound("trendAll.in=" + DEFAULT_TREND_ALL + "," + UPDATED_TREND_ALL);

        // Get all the csgoItemList where trendAll equals to UPDATED_TREND_ALL
        defaultCsgoItemShouldNotBeFound("trendAll.in=" + UPDATED_TREND_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByTrendAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where trendAll is not null
        defaultCsgoItemShouldBeFound("trendAll.specified=true");

        // Get all the csgoItemList where trendAll is null
        defaultCsgoItemShouldNotBeFound("trendAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolAllIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where volAll equals to DEFAULT_VOL_ALL
        defaultCsgoItemShouldBeFound("volAll.equals=" + DEFAULT_VOL_ALL);

        // Get all the csgoItemList where volAll equals to UPDATED_VOL_ALL
        defaultCsgoItemShouldNotBeFound("volAll.equals=" + UPDATED_VOL_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolAllIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where volAll in DEFAULT_VOL_ALL or UPDATED_VOL_ALL
        defaultCsgoItemShouldBeFound("volAll.in=" + DEFAULT_VOL_ALL + "," + UPDATED_VOL_ALL);

        // Get all the csgoItemList where volAll equals to UPDATED_VOL_ALL
        defaultCsgoItemShouldNotBeFound("volAll.in=" + UPDATED_VOL_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolAllIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where volAll is not null
        defaultCsgoItemShouldBeFound("volAll.specified=true");

        // Get all the csgoItemList where volAll is null
        defaultCsgoItemShouldNotBeFound("volAll.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolAllIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where volAll greater than or equals to DEFAULT_VOL_ALL
        defaultCsgoItemShouldBeFound("volAll.greaterOrEqualThan=" + DEFAULT_VOL_ALL);

        // Get all the csgoItemList where volAll greater than or equals to UPDATED_VOL_ALL
        defaultCsgoItemShouldNotBeFound("volAll.greaterOrEqualThan=" + UPDATED_VOL_ALL);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByVolAllIsLessThanSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where volAll less than or equals to DEFAULT_VOL_ALL
        defaultCsgoItemShouldNotBeFound("volAll.lessThan=" + DEFAULT_VOL_ALL);

        // Get all the csgoItemList where volAll less than or equals to UPDATED_VOL_ALL
        defaultCsgoItemShouldBeFound("volAll.lessThan=" + UPDATED_VOL_ALL);
    }


    @Test
    @Transactional
    public void getAllCsgoItemsByCfpIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where cfp equals to DEFAULT_CFP
        defaultCsgoItemShouldBeFound("cfp.equals=" + DEFAULT_CFP);

        // Get all the csgoItemList where cfp equals to UPDATED_CFP
        defaultCsgoItemShouldNotBeFound("cfp.equals=" + UPDATED_CFP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByCfpIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where cfp in DEFAULT_CFP or UPDATED_CFP
        defaultCsgoItemShouldBeFound("cfp.in=" + DEFAULT_CFP + "," + UPDATED_CFP);

        // Get all the csgoItemList where cfp equals to UPDATED_CFP
        defaultCsgoItemShouldNotBeFound("cfp.in=" + UPDATED_CFP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByCfpIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where cfp is not null
        defaultCsgoItemShouldBeFound("cfp.specified=true");

        // Get all the csgoItemList where cfp is null
        defaultCsgoItemShouldNotBeFound("cfp.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByIopIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where iop equals to DEFAULT_IOP
        defaultCsgoItemShouldBeFound("iop.equals=" + DEFAULT_IOP);

        // Get all the csgoItemList where iop equals to UPDATED_IOP
        defaultCsgoItemShouldNotBeFound("iop.equals=" + UPDATED_IOP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByIopIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where iop in DEFAULT_IOP or UPDATED_IOP
        defaultCsgoItemShouldBeFound("iop.in=" + DEFAULT_IOP + "," + UPDATED_IOP);

        // Get all the csgoItemList where iop equals to UPDATED_IOP
        defaultCsgoItemShouldNotBeFound("iop.in=" + UPDATED_IOP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByIopIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where iop is not null
        defaultCsgoItemShouldBeFound("iop.specified=true");

        // Get all the csgoItemList where iop is null
        defaultCsgoItemShouldNotBeFound("iop.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDcxIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dcx equals to DEFAULT_DCX
        defaultCsgoItemShouldBeFound("dcx.equals=" + DEFAULT_DCX);

        // Get all the csgoItemList where dcx equals to UPDATED_DCX
        defaultCsgoItemShouldNotBeFound("dcx.equals=" + UPDATED_DCX);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDcxIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dcx in DEFAULT_DCX or UPDATED_DCX
        defaultCsgoItemShouldBeFound("dcx.in=" + DEFAULT_DCX + "," + UPDATED_DCX);

        // Get all the csgoItemList where dcx equals to UPDATED_DCX
        defaultCsgoItemShouldNotBeFound("dcx.in=" + UPDATED_DCX);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByDcxIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where dcx is not null
        defaultCsgoItemShouldBeFound("dcx.specified=true");

        // Get all the csgoItemList where dcx is null
        defaultCsgoItemShouldNotBeFound("dcx.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOplpIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where oplp equals to DEFAULT_OPLP
        defaultCsgoItemShouldBeFound("oplp.equals=" + DEFAULT_OPLP);

        // Get all the csgoItemList where oplp equals to UPDATED_OPLP
        defaultCsgoItemShouldNotBeFound("oplp.equals=" + UPDATED_OPLP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOplpIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where oplp in DEFAULT_OPLP or UPDATED_OPLP
        defaultCsgoItemShouldBeFound("oplp.in=" + DEFAULT_OPLP + "," + UPDATED_OPLP);

        // Get all the csgoItemList where oplp equals to UPDATED_OPLP
        defaultCsgoItemShouldNotBeFound("oplp.in=" + UPDATED_OPLP);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOplpIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where oplp is not null
        defaultCsgoItemShouldBeFound("oplp.specified=true");

        // Get all the csgoItemList where oplp is null
        defaultCsgoItemShouldNotBeFound("oplp.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpqIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opq equals to DEFAULT_OPQ
        defaultCsgoItemShouldBeFound("opq.equals=" + DEFAULT_OPQ);

        // Get all the csgoItemList where opq equals to UPDATED_OPQ
        defaultCsgoItemShouldNotBeFound("opq.equals=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpqIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opq in DEFAULT_OPQ or UPDATED_OPQ
        defaultCsgoItemShouldBeFound("opq.in=" + DEFAULT_OPQ + "," + UPDATED_OPQ);

        // Get all the csgoItemList where opq equals to UPDATED_OPQ
        defaultCsgoItemShouldNotBeFound("opq.in=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpqIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opq is not null
        defaultCsgoItemShouldBeFound("opq.specified=true");

        // Get all the csgoItemList where opq is null
        defaultCsgoItemShouldNotBeFound("opq.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpqIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opq greater than or equals to DEFAULT_OPQ
        defaultCsgoItemShouldBeFound("opq.greaterOrEqualThan=" + DEFAULT_OPQ);

        // Get all the csgoItemList where opq greater than or equals to UPDATED_OPQ
        defaultCsgoItemShouldNotBeFound("opq.greaterOrEqualThan=" + UPDATED_OPQ);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByOpqIsLessThanSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where opq less than or equals to DEFAULT_OPQ
        defaultCsgoItemShouldNotBeFound("opq.lessThan=" + DEFAULT_OPQ);

        // Get all the csgoItemList where opq less than or equals to UPDATED_OPQ
        defaultCsgoItemShouldBeFound("opq.lessThan=" + UPDATED_OPQ);
    }


    @Test
    @Transactional
    public void getAllCsgoItemsByAddedIsEqualToSomething() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where added equals to DEFAULT_ADDED
        defaultCsgoItemShouldBeFound("added.equals=" + DEFAULT_ADDED);

        // Get all the csgoItemList where added equals to UPDATED_ADDED
        defaultCsgoItemShouldNotBeFound("added.equals=" + UPDATED_ADDED);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAddedIsInShouldWork() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where added in DEFAULT_ADDED or UPDATED_ADDED
        defaultCsgoItemShouldBeFound("added.in=" + DEFAULT_ADDED + "," + UPDATED_ADDED);

        // Get all the csgoItemList where added equals to UPDATED_ADDED
        defaultCsgoItemShouldNotBeFound("added.in=" + UPDATED_ADDED);
    }

    @Test
    @Transactional
    public void getAllCsgoItemsByAddedIsNullOrNotNull() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);

        // Get all the csgoItemList where added is not null
        defaultCsgoItemShouldBeFound("added.specified=true");

        // Get all the csgoItemList where added is null
        defaultCsgoItemShouldNotBeFound("added.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCsgoItemShouldBeFound(String filter) throws Exception {
        restCsgoItemMockMvc.perform(get("/api/csgo-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csgoItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.intValue())))
            .andExpect(jsonPath("$.[*].opm").value(hasItem(DEFAULT_OPM.booleanValue())))
            .andExpect(jsonPath("$.[*].vol").value(hasItem(DEFAULT_VOL)))
            .andExpect(jsonPath("$.[*].mp7").value(hasItem(DEFAULT_MP_7.intValue())))
            .andExpect(jsonPath("$.[*].avg7").value(hasItem(DEFAULT_AVG_7.intValue())))
            .andExpect(jsonPath("$.[*].lp7").value(hasItem(DEFAULT_LP_7.intValue())))
            .andExpect(jsonPath("$.[*].hp7").value(hasItem(DEFAULT_HP_7.intValue())))
            .andExpect(jsonPath("$.[*].mad7").value(hasItem(DEFAULT_MAD_7.intValue())))
            .andExpect(jsonPath("$.[*].dp7").value(hasItem(DEFAULT_DP_7.intValue())))
            .andExpect(jsonPath("$.[*].trend7").value(hasItem(DEFAULT_TREND_7.intValue())))
            .andExpect(jsonPath("$.[*].vol7").value(hasItem(DEFAULT_VOL_7)))
            .andExpect(jsonPath("$.[*].mp30").value(hasItem(DEFAULT_MP_30.intValue())))
            .andExpect(jsonPath("$.[*].avg30").value(hasItem(DEFAULT_AVG_30.intValue())))
            .andExpect(jsonPath("$.[*].lp30").value(hasItem(DEFAULT_LP_30.intValue())))
            .andExpect(jsonPath("$.[*].hp30").value(hasItem(DEFAULT_HP_30.intValue())))
            .andExpect(jsonPath("$.[*].mad30").value(hasItem(DEFAULT_MAD_30.intValue())))
            .andExpect(jsonPath("$.[*].dp30").value(hasItem(DEFAULT_DP_30.intValue())))
            .andExpect(jsonPath("$.[*].trend30").value(hasItem(DEFAULT_TREND_30.intValue())))
            .andExpect(jsonPath("$.[*].vol30").value(hasItem(DEFAULT_VOL_30)))
            .andExpect(jsonPath("$.[*].mpAll").value(hasItem(DEFAULT_MP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].avgAll").value(hasItem(DEFAULT_AVG_ALL.intValue())))
            .andExpect(jsonPath("$.[*].lpAll").value(hasItem(DEFAULT_LP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].hpAll").value(hasItem(DEFAULT_HP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].madAll").value(hasItem(DEFAULT_MAD_ALL.intValue())))
            .andExpect(jsonPath("$.[*].dpAll").value(hasItem(DEFAULT_DP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].trendAll").value(hasItem(DEFAULT_TREND_ALL.intValue())))
            .andExpect(jsonPath("$.[*].volAll").value(hasItem(DEFAULT_VOL_ALL)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].added").value(hasItem(DEFAULT_ADDED.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCsgoItemShouldNotBeFound(String filter) throws Exception {
        restCsgoItemMockMvc.perform(get("/api/csgo-items?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingCsgoItem() throws Exception {
        // Get the csgoItem
        restCsgoItemMockMvc.perform(get("/api/csgo-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsgoItem() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);
        csgoItemSearchRepository.save(csgoItem);
        int databaseSizeBeforeUpdate = csgoItemRepository.findAll().size();

        // Update the csgoItem
        CsgoItem updatedCsgoItem = csgoItemRepository.findOne(csgoItem.getId());
        updatedCsgoItem
            .name(UPDATED_NAME)
            .sp(UPDATED_SP)
            .opm(UPDATED_OPM)
            .vol(UPDATED_VOL)
            .mp7(UPDATED_MP_7)
            .avg7(UPDATED_AVG_7)
            .lp7(UPDATED_LP_7)
            .hp7(UPDATED_HP_7)
            .mad7(UPDATED_MAD_7)
            .dp7(UPDATED_DP_7)
            .trend7(UPDATED_TREND_7)
            .vol7(UPDATED_VOL_7)
            .mp30(UPDATED_MP_30)
            .avg30(UPDATED_AVG_30)
            .lp30(UPDATED_LP_30)
            .hp30(UPDATED_HP_30)
            .mad30(UPDATED_MAD_30)
            .dp30(UPDATED_DP_30)
            .trend30(UPDATED_TREND_30)
            .vol30(UPDATED_VOL_30)
            .mpAll(UPDATED_MP_ALL)
            .avgAll(UPDATED_AVG_ALL)
            .lpAll(UPDATED_LP_ALL)
            .hpAll(UPDATED_HP_ALL)
            .madAll(UPDATED_MAD_ALL)
            .dpAll(UPDATED_DP_ALL)
            .trendAll(UPDATED_TREND_ALL)
            .volAll(UPDATED_VOL_ALL)
            .cfp(UPDATED_CFP)
            .iop(UPDATED_IOP)
            .dcx(UPDATED_DCX)
            .oplp(UPDATED_OPLP)
            .opq(UPDATED_OPQ)
            .added(UPDATED_ADDED);
        CsgoItemDTO csgoItemDTO = csgoItemMapper.toDto(updatedCsgoItem);

        restCsgoItemMockMvc.perform(put("/api/csgo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(csgoItemDTO)))
            .andExpect(status().isOk());

        // Validate the CsgoItem in the database
        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeUpdate);
        CsgoItem testCsgoItem = csgoItemList.get(csgoItemList.size() - 1);
        assertThat(testCsgoItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCsgoItem.getSp()).isEqualTo(UPDATED_SP);
        assertThat(testCsgoItem.isOpm()).isEqualTo(UPDATED_OPM);
        assertThat(testCsgoItem.getVol()).isEqualTo(UPDATED_VOL);
        assertThat(testCsgoItem.getMp7()).isEqualTo(UPDATED_MP_7);
        assertThat(testCsgoItem.getAvg7()).isEqualTo(UPDATED_AVG_7);
        assertThat(testCsgoItem.getLp7()).isEqualTo(UPDATED_LP_7);
        assertThat(testCsgoItem.getHp7()).isEqualTo(UPDATED_HP_7);
        assertThat(testCsgoItem.getMad7()).isEqualTo(UPDATED_MAD_7);
        assertThat(testCsgoItem.getDp7()).isEqualTo(UPDATED_DP_7);
        assertThat(testCsgoItem.getTrend7()).isEqualTo(UPDATED_TREND_7);
        assertThat(testCsgoItem.getVol7()).isEqualTo(UPDATED_VOL_7);
        assertThat(testCsgoItem.getMp30()).isEqualTo(UPDATED_MP_30);
        assertThat(testCsgoItem.getAvg30()).isEqualTo(UPDATED_AVG_30);
        assertThat(testCsgoItem.getLp30()).isEqualTo(UPDATED_LP_30);
        assertThat(testCsgoItem.getHp30()).isEqualTo(UPDATED_HP_30);
        assertThat(testCsgoItem.getMad30()).isEqualTo(UPDATED_MAD_30);
        assertThat(testCsgoItem.getDp30()).isEqualTo(UPDATED_DP_30);
        assertThat(testCsgoItem.getTrend30()).isEqualTo(UPDATED_TREND_30);
        assertThat(testCsgoItem.getVol30()).isEqualTo(UPDATED_VOL_30);
        assertThat(testCsgoItem.getMpAll()).isEqualTo(UPDATED_MP_ALL);
        assertThat(testCsgoItem.getAvgAll()).isEqualTo(UPDATED_AVG_ALL);
        assertThat(testCsgoItem.getLpAll()).isEqualTo(UPDATED_LP_ALL);
        assertThat(testCsgoItem.getHpAll()).isEqualTo(UPDATED_HP_ALL);
        assertThat(testCsgoItem.getMadAll()).isEqualTo(UPDATED_MAD_ALL);
        assertThat(testCsgoItem.getDpAll()).isEqualTo(UPDATED_DP_ALL);
        assertThat(testCsgoItem.getTrendAll()).isEqualTo(UPDATED_TREND_ALL);
        assertThat(testCsgoItem.getVolAll()).isEqualTo(UPDATED_VOL_ALL);
        assertThat(testCsgoItem.getCfp()).isEqualTo(UPDATED_CFP);
        assertThat(testCsgoItem.getIop()).isEqualTo(UPDATED_IOP);
        assertThat(testCsgoItem.getDcx()).isEqualTo(UPDATED_DCX);
        assertThat(testCsgoItem.getOplp()).isEqualTo(UPDATED_OPLP);
        assertThat(testCsgoItem.getOpq()).isEqualTo(UPDATED_OPQ);
        assertThat(testCsgoItem.getAdded()).isEqualTo(UPDATED_ADDED);

        // Validate the CsgoItem in Elasticsearch
        CsgoItem csgoItemEs = csgoItemSearchRepository.findOne(testCsgoItem.getId());
        assertThat(csgoItemEs).isEqualToComparingFieldByField(testCsgoItem);
    }

    @Test
    @Transactional
    public void updateNonExistingCsgoItem() throws Exception {
        int databaseSizeBeforeUpdate = csgoItemRepository.findAll().size();

        // Create the CsgoItem
        CsgoItemDTO csgoItemDTO = csgoItemMapper.toDto(csgoItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCsgoItemMockMvc.perform(put("/api/csgo-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(csgoItemDTO)))
            .andExpect(status().isCreated());

        // Validate the CsgoItem in the database
        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCsgoItem() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);
        csgoItemSearchRepository.save(csgoItem);
        int databaseSizeBeforeDelete = csgoItemRepository.findAll().size();

        // Get the csgoItem
        restCsgoItemMockMvc.perform(delete("/api/csgo-items/{id}", csgoItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean csgoItemExistsInEs = csgoItemSearchRepository.exists(csgoItem.getId());
        assertThat(csgoItemExistsInEs).isFalse();

        // Validate the database is empty
        List<CsgoItem> csgoItemList = csgoItemRepository.findAll();
        assertThat(csgoItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCsgoItem() throws Exception {
        // Initialize the database
        csgoItemRepository.saveAndFlush(csgoItem);
        csgoItemSearchRepository.save(csgoItem);

        // Search the csgoItem
        restCsgoItemMockMvc.perform(get("/api/_search/csgo-items?query=id:" + csgoItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csgoItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sp").value(hasItem(DEFAULT_SP.intValue())))
            .andExpect(jsonPath("$.[*].opm").value(hasItem(DEFAULT_OPM.booleanValue())))
            .andExpect(jsonPath("$.[*].vol").value(hasItem(DEFAULT_VOL)))
            .andExpect(jsonPath("$.[*].mp7").value(hasItem(DEFAULT_MP_7.intValue())))
            .andExpect(jsonPath("$.[*].avg7").value(hasItem(DEFAULT_AVG_7.intValue())))
            .andExpect(jsonPath("$.[*].lp7").value(hasItem(DEFAULT_LP_7.intValue())))
            .andExpect(jsonPath("$.[*].hp7").value(hasItem(DEFAULT_HP_7.intValue())))
            .andExpect(jsonPath("$.[*].mad7").value(hasItem(DEFAULT_MAD_7.intValue())))
            .andExpect(jsonPath("$.[*].dp7").value(hasItem(DEFAULT_DP_7.intValue())))
            .andExpect(jsonPath("$.[*].trend7").value(hasItem(DEFAULT_TREND_7.intValue())))
            .andExpect(jsonPath("$.[*].vol7").value(hasItem(DEFAULT_VOL_7)))
            .andExpect(jsonPath("$.[*].mp30").value(hasItem(DEFAULT_MP_30.intValue())))
            .andExpect(jsonPath("$.[*].avg30").value(hasItem(DEFAULT_AVG_30.intValue())))
            .andExpect(jsonPath("$.[*].lp30").value(hasItem(DEFAULT_LP_30.intValue())))
            .andExpect(jsonPath("$.[*].hp30").value(hasItem(DEFAULT_HP_30.intValue())))
            .andExpect(jsonPath("$.[*].mad30").value(hasItem(DEFAULT_MAD_30.intValue())))
            .andExpect(jsonPath("$.[*].dp30").value(hasItem(DEFAULT_DP_30.intValue())))
            .andExpect(jsonPath("$.[*].trend30").value(hasItem(DEFAULT_TREND_30.intValue())))
            .andExpect(jsonPath("$.[*].vol30").value(hasItem(DEFAULT_VOL_30)))
            .andExpect(jsonPath("$.[*].mpAll").value(hasItem(DEFAULT_MP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].avgAll").value(hasItem(DEFAULT_AVG_ALL.intValue())))
            .andExpect(jsonPath("$.[*].lpAll").value(hasItem(DEFAULT_LP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].hpAll").value(hasItem(DEFAULT_HP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].madAll").value(hasItem(DEFAULT_MAD_ALL.intValue())))
            .andExpect(jsonPath("$.[*].dpAll").value(hasItem(DEFAULT_DP_ALL.intValue())))
            .andExpect(jsonPath("$.[*].trendAll").value(hasItem(DEFAULT_TREND_ALL.intValue())))
            .andExpect(jsonPath("$.[*].volAll").value(hasItem(DEFAULT_VOL_ALL)))
            .andExpect(jsonPath("$.[*].cfp").value(hasItem(DEFAULT_CFP.doubleValue())))
            .andExpect(jsonPath("$.[*].iop").value(hasItem(DEFAULT_IOP.doubleValue())))
            .andExpect(jsonPath("$.[*].dcx").value(hasItem(DEFAULT_DCX.doubleValue())))
            .andExpect(jsonPath("$.[*].oplp").value(hasItem(DEFAULT_OPLP.doubleValue())))
            .andExpect(jsonPath("$.[*].opq").value(hasItem(DEFAULT_OPQ)))
            .andExpect(jsonPath("$.[*].added").value(hasItem(DEFAULT_ADDED.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsgoItem.class);
        CsgoItem csgoItem1 = new CsgoItem();
        csgoItem1.setId(1L);
        CsgoItem csgoItem2 = new CsgoItem();
        csgoItem2.setId(csgoItem1.getId());
        assertThat(csgoItem1).isEqualTo(csgoItem2);
        csgoItem2.setId(2L);
        assertThat(csgoItem1).isNotEqualTo(csgoItem2);
        csgoItem1.setId(null);
        assertThat(csgoItem1).isNotEqualTo(csgoItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsgoItemDTO.class);
        CsgoItemDTO csgoItemDTO1 = new CsgoItemDTO();
        csgoItemDTO1.setId(1L);
        CsgoItemDTO csgoItemDTO2 = new CsgoItemDTO();
        assertThat(csgoItemDTO1).isNotEqualTo(csgoItemDTO2);
        csgoItemDTO2.setId(csgoItemDTO1.getId());
        assertThat(csgoItemDTO1).isEqualTo(csgoItemDTO2);
        csgoItemDTO2.setId(2L);
        assertThat(csgoItemDTO1).isNotEqualTo(csgoItemDTO2);
        csgoItemDTO1.setId(null);
        assertThat(csgoItemDTO1).isNotEqualTo(csgoItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(csgoItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(csgoItemMapper.fromId(null)).isNull();
    }
}
