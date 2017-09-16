package com.mma.mma.web.rest;

import com.mma.mma.MmaApp;

import com.mma.mma.domain.CsgoItem;
import com.mma.mma.repository.CsgoItemRepository;
import com.mma.mma.service.CsgoItemService;
import com.mma.mma.repository.search.CsgoItemSearchRepository;
import com.mma.mma.service.dto.CsgoItemDTO;
import com.mma.mma.service.mapper.CsgoItemMapper;
import com.mma.mma.web.rest.errors.ExceptionTranslator;

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
        final CsgoItemResource csgoItemResource = new CsgoItemResource(csgoItemService);
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
            .andExpect(jsonPath("$.added").value(DEFAULT_ADDED.toString()));
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
