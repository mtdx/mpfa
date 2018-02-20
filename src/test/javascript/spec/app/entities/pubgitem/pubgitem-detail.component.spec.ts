/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { MmaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PubgitemDetailComponent } from '../../../../../../main/webapp/app/entities/pubgitem/pubgitem-detail.component';
import { PubgitemService } from '../../../../../../main/webapp/app/entities/pubgitem/pubgitem.service';
import { Pubgitem } from '../../../../../../main/webapp/app/entities/pubgitem/pubgitem.model';

describe('Component Tests', () => {

    describe('Pubgitem Management Detail Component', () => {
        let comp: PubgitemDetailComponent;
        let fixture: ComponentFixture<PubgitemDetailComponent>;
        let service: PubgitemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [MmaTestModule],
                declarations: [PubgitemDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PubgitemService,
                    JhiEventManager
                ]
            }).overrideTemplate(PubgitemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PubgitemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PubgitemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Pubgitem(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.pubgitem).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
